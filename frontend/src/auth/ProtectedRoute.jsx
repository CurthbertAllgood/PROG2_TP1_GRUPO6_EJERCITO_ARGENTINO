import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "./UserContext";

export default function ProtectedRoute({ children, roles }) {
  const { token, loading, hasRole } = useAuth();
  if (loading) return <div style={{ padding: 24 }}>Cargando…</div>;
  if (!token) return <Navigate to="/login" replace />;
  if (roles && !hasRole(...roles)) return <Navigate to="/forbidden" replace />;
  return children;
}
