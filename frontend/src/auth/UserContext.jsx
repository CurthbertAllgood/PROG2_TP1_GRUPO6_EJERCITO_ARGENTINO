import React, { createContext, useContext, useEffect, useMemo, useState, useCallback } from "react";
import { authApi } from "./api";
import { tokenStore } from "./http";

const Ctx = createContext(null);
export const useAuth = () => useContext(Ctx);

function decodeJwt(t) {
  try {
    const payload = JSON.parse(atob(t.split(".")[1]));
    return { username: payload.sub, role: payload.role, personaId: payload.personaId };
  } catch {
    return null;
  }
}

export function UserProvider({ children }) {
  const [token, setToken] = useState(tokenStore.get());
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Restaurar sesión al montar
  useEffect(() => {
    (async () => {
      if (!token) { setLoading(false); return; }
      try {
        let me;
        try { me = await authApi.me(); }
        catch { me = decodeJwt(token); }
        setUser(me);
      } finally { setLoading(false); }
    })();
  }, [token]);

  const login = useCallback(async (username, password) => {
    const { token: t } = await authApi.login(username, password);
    tokenStore.set(t);
    setToken(t);
    let me;
    try { me = await authApi.me(); }
    catch { me = decodeJwt(t); }
    setUser(me);
  }, [setToken, setUser]);

  const register = useCallback(async (payload) => {
    const { token: t } = await authApi.register(payload);
    tokenStore.set(t);
    setToken(t);
    let me;
    try { me = await authApi.me(); }
    catch { me = decodeJwt(t); }
    setUser(me);
  }, [setToken, setUser]);

  const logout = useCallback(() => {
    tokenStore.clear();
    setToken(null);
    setUser(null);
  }, [setToken, setUser]);

  const hasRole = useCallback((...roles) => !!user && roles.includes(user.role), [user]);

  const value = useMemo(
    () => ({ token, user, loading, login, register, logout, hasRole }),
    [token, user, loading, login, register, logout, hasRole]
  );

  return <Ctx.Provider value={value}>{children}</Ctx.Provider>;
}
