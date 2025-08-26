// src/pages/OficialDashboard.jsx
import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../auth/UserContext";
import "../assets/styles/GenericDashboard.css";

export default function OficialDashboard() {
  const { user } = useAuth();

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h2>Bienvenido, {user?.username}</h2>
      </div>

      <section className="panel" style={{ marginBottom: 16 }}>
        <h3>Panel del Oficial</h3>
        <ul style={{ marginTop: 12, lineHeight: 1.7 }}>
          <li>🛠 Administrar soldados y servicios</li>
          <li>🧑‍💻 Control total del sistema</li>
          <li>📊 Acceso completo a estadísticas</li>
        </ul>
      </section>

      <div className="grid-2">
        <section className="panel">
          <h3>Accesos rápidos</h3>
          <div style={{ marginTop: 12, display: "grid", gap: 8 }}>
            <Link className="btn btn-outline btn-block" to="/cuerpos">Cuerpos (ABM)</Link>
            <Link className="btn btn-outline btn-block" to="/companias">Compañías (ABM)</Link>
            <Link className="btn btn-outline btn-block" to="/cuarteles">Cuarteles (ABM)</Link>
            <Link className="btn btn-outline btn-block" to="/militares">Militares (ABM)</Link>
            <Link className="btn btn-outline btn-block" to="/services">Servicios</Link>
          </div>
        </section>

        <section className="panel">
          <h3>Estado</h3>
          <p className="help" style={{ marginTop: 12 }}>
            Próximamente: métricas/resumen (total de militares, servicios activos,
            asignaciones pendientes, etc.).
          </p>
        </section>
      </div>
    </div>
  );
}
