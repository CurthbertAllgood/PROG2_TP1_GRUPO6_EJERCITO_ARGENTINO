import React, { useEffect, useState } from "react";
import { useAuth } from "../auth/UserContext";
import { http } from "../auth/http";
import "../assets/styles/SoldierDashboard.css";
import "../assets/styles/GenericDashboard.css";

export default function SoldierDashboard() {
  const { user } = useAuth();

  const [militar, setMilitar] = useState(null);
  const [realizados, setRealizados] = useState([]);
  const [pendientes, setPendientes] = useState([]);

  const [loading, setLoading] = useState(true);
  const [msg, setMsg] = useState("");
  const [err, setErr] = useState("");
  const [markingId, setMarkingId] = useState(null);

  const personaId = user?.personaId;
  const isMilitarRole = user && ["SOLDADO", "SUBOFICIAL", "OFICIAL"].includes(user.role);

  const loadAll = async () => {
    if (!personaId) {
      setErr("Tu usuario no tiene personaId en el token (no está vinculado a un Militar).");
      setLoading(false);
      return;
    }
    try {
      setLoading(true);
      setErr(""); setMsg("");

      // Ficha del militar
      const m = await http.get(`/api/militares/${personaId}`);
      setMilitar(m.data);

      // Realizados históricos
      const r = await http.get("/api/servicios-realizados", { params: { militarId: personaId } });
      setRealizados(r.data ?? []);

      // Asignaciones pendientes (el backend igual filtra por personaId si sos SOLDADO)
      const p = await http.get("/api/asignaciones", { params: { estado: "PENDIENTE" } });
      setPendientes(p.data ?? []);
    } catch (e) {
      const msg =
        e?.response?.data?.error ||
        e?.response?.data?.message ||
        e?.message ||
        "No se pudo cargar tu información.";
      setErr(msg);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (isMilitarRole) loadAll();
    else { setLoading(false); setErr(""); setMsg(""); }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [isMilitarRole, personaId]);

  const marcarRealizado = async (asignacionId) => {
    setMarkingId(asignacionId);
    setErr(""); setMsg("");
    try {
      await http.patch(`/api/asignaciones/${asignacionId}/realizar`, {}); // usa fecha de hoy por defecto
      setMsg("Servicio marcado como realizado.");
      // refrescamos pendientes + realizados
      const [p, r] = await Promise.all([
        http.get("/api/asignaciones", { params: { estado: "PENDIENTE" } }),
        http.get("/api/servicios-realizados", { params: { militarId: personaId } }),
      ]);
      setPendientes(p.data ?? []);
      setRealizados(r.data ?? []);
    } catch (e) {
      const m =
        e?.response?.data?.error ||
        e?.response?.data?.message ||
        e?.message ||
        "No se pudo marcar como realizado.";
      setErr(m);
    } finally {
      setMarkingId(null);
    }
  };

  if (!isMilitarRole) {
    return (
      <div className="dashboard-container">
        <h2>Panel de Persona</h2>
        <p>Tu rol es <b>{user?.role}</b>. No hay datos militares para mostrar.</p>
      </div>
    );
  }

  if (loading) return <p style={{ padding: 24 }}>Cargando…</p>;
  if (err) return <p style={{ padding: 24, color: "crimson" }}>{err}</p>;
  if (!militar) return <p style={{ padding: 24 }}>No encontramos tu ficha de militar.</p>;

  return (
    <div className="soldier-dashboard dashboard-container">
      <div className="dashboard-header">
        <h2>Mis servicios</h2>
      </div>

      {msg && (
        <div style={{ background: "#e7ffef", border: "1px solid #b7e3c6", color: "#215b36", padding: 10, borderRadius: 8, marginBottom: 12 }}>
          {msg}
        </div>
      )}

      <section style={{ border: "1px solid #eee", borderRadius: 12, padding: 12, marginBottom: 16 }}>
        <h3 style={{ marginTop: 0 }}>
          {militar.nombre} {militar.apellido}
        </h3>
        <div style={{ display: "flex", gap: 16, flexWrap: "wrap", fontSize: 14, opacity: 0.85 }}>
          <span><b>Código:</b> {militar.codigoMilitar || "-"}</span>
          <span><b>Cuerpo:</b> {militar.cuerpoNombre || "-"}</span>
          <span><b>Compañía:</b> {militar.companiaNombre || "-"}</span>
          <span><b>Cuartel:</b> {militar.cuartelNombre || "-"}</span>
        </div>
      </section>

      <div style={{ display: "grid", gap: 16, gridTemplateColumns: "1fr 1fr" }}>
        {/* Pendientes */}
        <section style={{ border: "1px solid #eee", borderRadius: 12, padding: 12 }}>
          <h3 style={{ marginTop: 0 }}>Asignaciones pendientes</h3>
          {(!pendientes || pendientes.length === 0) ? (
            <p style={{ opacity: 0.7 }}>No tenés servicios pendientes.</p>
          ) : (
            <table style={{ width: "100%", borderCollapse: "collapse" }}>
              <thead>
                <tr style={{ textAlign: "left", borderBottom: "1px solid #eee" }}>
                  <th style={{ padding: "8px 6px" }}>Servicio</th>
                  <th style={{ padding: "8px 6px" }}>Fecha</th>
                  <th style={{ padding: "8px 6px" }}></th>
                </tr>
              </thead>
              <tbody>
                {pendientes.map(p => (
                  <tr key={p.id} style={{ borderBottom: "1px solid #f4f4f4" }}>
                    <td style={{ padding: "8px 6px" }}>[{p.servicioCodigo}] {p.servicioDescripcion}</td>
                    <td style={{ padding: "8px 6px", whiteSpace: "nowrap" }}>
                      {p.fechaProgramada ? new Date(p.fechaProgramada).toLocaleDateString() : "-"}
                    </td>
                    <td style={{ padding: "8px 6px" }}>
                      <button onClick={() => marcarRealizado(p.id)} disabled={markingId === p.id}>
                        {markingId === p.id ? "Marcando…" : "Marcar realizado"}
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
          <small style={{ display: "block", marginTop: 8, opacity: 0.7 }}>
            Al marcar realizado, se registra en tu historial y desaparece de pendientes.
          </small>
        </section>

        {/* Realizados */}
        <section style={{ border: "1px solid #eee", borderRadius: 12, padding: 12 }}>
          <h3 style={{ marginTop: 0 }}>Servicios realizados</h3>
          {(!realizados || realizados.length === 0) ? (
            <p style={{ opacity: 0.7 }}>Aún no tenés servicios registrados.</p>
          ) : (
            <table style={{ width: "100%", borderCollapse: "collapse" }}>
              <thead>
                <tr style={{ textAlign: "left", borderBottom: "1px solid #eee" }}>
                  <th style={{ padding: "8px 6px" }}>Código</th>
                  <th style={{ padding: "8px 6px" }}>Descripción</th>
                  <th style={{ padding: "8px 6px" }}>Fecha</th>
                </tr>
              </thead>
              <tbody>
                {realizados.map(r => (
                  <tr key={r.id} style={{ borderBottom: "1px solid #f4f4f4" }}>
                    <td style={{ padding: "8px 6px" }}>{r.servicioCodigo}</td>
                    <td style={{ padding: "8px 6px" }}>{r.servicioDescripcion}</td>
                    <td style={{ padding: "8px 6px", whiteSpace: "nowrap" }}>
                      {r.fecha ? new Date(r.fecha).toLocaleDateString() : "-"}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </section>
      </div>
    </div>
  );
}
