// src/pages/SuboficialDashboard.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from "../auth/UserContext";
import '../assets/styles/GenericDashboard.css';

const SuboficialDashboard = () => {
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

      <h3>Panel del Suboficial</h3>
      <ul>
        <li>📋 Ver soldados a cargo</li>
        <li>🧾 Revisar servicios</li>
        <li>🔒 Sin permisos de modificación</li>
      </ul>
    </div>
  );
};

export default SuboficialDashboard;
