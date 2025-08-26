import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../auth/UserContext";

export default function Navbar() {
  const { user, logout, hasRole } = useAuth();
  return (
    <nav style={{ display: "flex", gap: 16, padding: 12, borderBottom: "1px solid #ddd" }}>
      <Link to="/">Inicio</Link>
      <Link to="/services">Servicios</Link>

      {hasRole("SUBOFICIAL","OFICIAL") && (
        <>
          <Link to="/cuerpos">Cuerpos (ABM)</Link>
          <Link to="/companias">Compañías (ABM)</Link>
          <Link to="/cuarteles">Cuarteles (ABM)</Link>
          <Link to="/militares">Militares (ABM)</Link>
        </>
      )}

      <div style={{ marginLeft: "auto" }}>
        {user ? (
          <>
            <span style={{ marginRight: 12 }}>{user.username} — {user.role}</span>
            <button onClick={logout}>Salir</button>
          </>
        ) : <Link to="/login">Login</Link>}
      </div>
    </nav>
  );
}