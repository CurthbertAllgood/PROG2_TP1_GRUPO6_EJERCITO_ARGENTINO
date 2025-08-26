import React, { useState } from "react";
import { useAuth } from "../auth/UserContext";
import { useNavigate } from "react-router-dom";

export default function LoginPage() {
  const { login } = useAuth();
  const nav = useNavigate();
  const [form, setForm] = useState({ username: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const onSubmit = async (e) => {
    e.preventDefault();
    if (loading) return;
    setError("");
    setLoading(true);
    try {
      await login(form.username, form.password);
      nav("/"); // Redirige al dashboard
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
    <>
      <form
        onSubmit={onSubmit}
        style={{ maxWidth: 360, margin: "6rem auto 1rem", display: "grid", gap: 8 }}
      >
        <h2>Ingresar</h2>

        <input
          placeholder="Usuario"
          required
          value={form.username}
          onChange={(e) => setForm({ ...form, username: e.target.value })}
        />

        <input
          type="password"
          placeholder="Contraseña"
          required
          value={form.password}
          onChange={(e) => setForm({ ...form, password: e.target.value })}
        />

        <button disabled={loading}>{loading ? "Entrando..." : "Entrar"}</button>

        {error && <p style={{ color: "crimson" }}>{error}</p>}
      </form>

      {/* Usuarios de prueba */}
      <section className="panel" style={{ maxWidth: 360, margin: "0 auto" }}>
        <details open>
          <summary style={{ cursor: "pointer", fontWeight: 700 }}>
            Usuarios de prueba
          </summary>

          <p className="muted" style={{ marginTop: 8 }}>
            <small>
              Formato: <b>usuario</b> / <b>contraseña</b>
            </small>
          </p>

          <ul
            style={{
              listStyle: "none",
              padding: 0,
              marginTop: 12,
              display: "grid",
              gap: 8,
            }}
          >
            <li>
              <span className="badge badge-primary" style={{ marginRight: 8 }}>
                SOLDADO
              </span>
              <code>soldado_ortiz</code> / <code>123</code>
            </li>
            <li>
              <span className="badge badge-primary" style={{ marginRight: 8 }}>
                SUBOFICIAL
              </span>
              <code>suboficial_garcia</code> / <code>1234</code>
            </li>
            <li>
              <span className="badge badge-primary" style={{ marginRight: 8 }}>
                OFICIAL
              </span>
              <code>jefe</code> / <code>jefe123</code>
            </li>
          </ul>
        </details>
      </section>
    </>
  );
}
