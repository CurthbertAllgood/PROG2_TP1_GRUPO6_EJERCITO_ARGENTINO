import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { UserProvider } from "./auth/UserContext";
import ProtectedRoute from "./auth/ProtectedRoute";
import Navbar from "./components/Navbar";

// Páginas que ya tenés en tu proyecto
import LoginPage from "./pages/LoginPage";
import Dashboard from "./pages/Dashboard";
import ServicesPages from "./pages/ServicesPages";
import OfficialDashboard from "./pages/OfficialDashboard";
import SuboficialDashboard from "./pages/SuboficialDashboard";
import SoldierDashboard from "./pages/SoldierDashboard";

function Forbidden() {
  return <div style={{ padding: 24 }}><h2>403 — No tenés permiso</h2></div>;
}

export default function App() {
  return (
    <BrowserRouter>
      <UserProvider>
        <Navbar />
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/forbidden" element={<Forbidden />} />

          <Route
            path="/"
            element={
              <ProtectedRoute>
                <Dashboard />
              </ProtectedRoute>
            }
          />

          <Route
            path="/services"
            element={
              <ProtectedRoute>
                <ServicesPages />
              </ProtectedRoute>
            }
          />

          <Route
            path="/oficial"
            element={
              <ProtectedRoute roles={["OFICIAL"]}>
                <OfficialDashboard />
              </ProtectedRoute>
            }
          />

          <Route
            path="/suboficial"
            element={
              <ProtectedRoute roles={["SUBOFICIAL","OFICIAL"]}>
                <SuboficialDashboard />
              </ProtectedRoute>
            }
          />

          <Route
            path="/soldado"
            element={
              <ProtectedRoute roles={["SOLDADO","SUBOFICIAL","OFICIAL"]}>
                <SoldierDashboard />
              </ProtectedRoute>
            }
          />
        </Routes>
      </UserProvider>
    </BrowserRouter>
  );
}
