import { useAuth } from "../auth/UserContext";
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";

export default function LoginPage() {
  const { login } = useAuth();
  const nav = useNavigate();
  const [form, setForm] = useState({ username: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const onSubmit = async (e) => {
    e.preventDefault();
    if (loading) return;
    setError(""); setLoading(true);
    try {
      await login(form.username, form.password);
      nav("/"); // si querés, redirigí según rol en el Dashboard
    } catch (err) {
      const msg =
        err?.response?.data?.error ||
        err?.response?.data?.message ||
        err?.message ||
        "Error de autenticación";
      setError(msg);
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={onSubmit} style={{ maxWidth: 360, margin: "6rem auto", display: "grid", gap: 8 }}>
      <h2>Ingresar</h2>
      <input
        placeholder="Usuario"
        required
        value={form.username}
        onChange={(e)=>setForm({...form, username:e.target.value})}
      />
      <input
        type="password"
        placeholder="Contraseña"
        required
        value={form.password}
        onChange={(e)=>setForm({...form, password:e.target.value})}
      />
      <button disabled={loading}>{loading ? "Entrando..." : "Entrar"}</button>
      {error && <p style={{color:"crimson"}}>{error}</p>}
    </form>
  );
}
