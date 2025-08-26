// src/pages/OficialDashboard.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from "../auth/UserContext";
import '../assets/styles/GenericDashboard.css';

const OficialDashboard = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h2>Bienvenido, {user?.username}</h2>
        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </div>

      <h3>Panel del Oficial</h3>
      <ul>
        <li>🛠 Administrar soldados y servicios</li>
        <li>🧑‍💻 Control total del sistema</li>
        <li>📊 Acceso completo a estadísticas</li>
      </ul>
    </div>
  );
};

export default OficialDashboard;
