import { http } from "./http";

export const authApi = {
  login: (username, password) =>
    http.post("/auth/login", { username, password }).then((r) => r.data),

  register: (payload) =>
    http.post("/auth/register", payload).then((r) => r.data),

  // Si tu backend aún no tiene /auth/me, el contexto hace fallback decodificando el JWT
  me: () => http.get("/auth/me").then((r) => r.data),
};
