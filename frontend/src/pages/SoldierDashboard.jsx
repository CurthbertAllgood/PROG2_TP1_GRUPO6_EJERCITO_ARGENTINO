// src/pages/SoldierDashboard.jsx
import React, { useEffect, useState } from 'react';
import { useUser } from '../auth/UserContext';
import Navbar from '../components/Navbar';
import axios from 'axios';
import '../assets/styles/SoldierDashboard.css';
import '../assets/styles/GenericDashboard.css';

const SoldierDashboard = () => {
  const [soldado, setSoldado] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
 const { user } = useAuth();

  const API_URL = process.env.REACT_APP_API_URL;

  useEffect(() => {
    if (!user || !user.militarId) {
      setError('Usuario no identificado.');
      setLoading(false);
      return;
    }

    console.log('Fetching soldado con ID:', user.militarId);

    axios.get(`${API_URL}/soldados/${user.militarId}`)
      .then((res) => {
        setSoldado(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error('Error al obtener datos del soldado:', err);
        setError('No se pudo cargar la información del soldado.');
        setLoading(false);
      });
  }, [user, API_URL]);

  if (loading) return <p>Cargando información...</p>;
  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  if (!soldado) return <p>No se encontró información del soldado.</p>;

  return (
    <>
      <Navbar />
      <div className="soldier-dashboard dashboard-container">
        <h2>Bienvenido, {soldado.nombre} {soldado.apellido}</h2>

        <div className="soldier-info">
          <p><strong>Graduación:</strong> {soldado.graduacion || 'N/A'}</p>
          <p><strong>Cuerpo:</strong> {soldado.cuerpo?.nombre || 'N/A'}</p>
          <p><strong>Compañía:</strong> {soldado.compania?.nombre || 'N/A'}</p>
          <p><strong>Cuartel:</strong> {soldado.cuartel?.nombre || 'N/A'}</p>
        </div>

        <div className="servicios-section">
          <h3>Servicios realizados</h3>
          {soldado.servicios?.length > 0 ? (
            <ul>
              {soldado.servicios.map((serv) => (
                <li key={serv.id}>
                  <span className="servicio-desc">{serv.descripcion}</span>
                  <span className="servicio-fecha">
                    {new Date(serv.fecha).toLocaleDateString()}
                  </span>
                </li>
              ))}
            </ul>
          ) : (
            <p>No hay servicios registrados.</p>
          )}
        </div>
      </div>
    </>
  );
};

export default SoldierDashboard;
