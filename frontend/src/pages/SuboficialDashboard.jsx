import React, { useEffect, useMemo, useState } from "react";
import { useAuth } from "../auth/UserContext";
import { http } from "../auth/http";
import "../assets/styles/GenericDashboard.css";

export default function SuboficialDashboard() {
  const { user } = useAuth();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Catálogos
  const [militares, setMilitares] = useState([]);
  const [servicios, setServicios] = useState([]);

  // Selección actual
  const [busqueda, setBusqueda] = useState("");
  const [militarId, setMilitarId] = useState(null);
  const [militarSel, setMilitarSel] = useState(null);
  const [realizados, setRealizados] = useState([]);

  // Form nuevo "realizado"
  const [form, setForm] = useState({
    servicioId: "",
    fecha: "", // yyyy-MM-dd (input type="date")
  });
  const [saving, setSaving] = useState(false);

  const isSubo = user && (user.role === "SUBOFICIAL" || user.role === "OFICIAL");

  // --- LOADERS ---
  const loadMilitares = async () => {
    const res = await http.get("/api/militares");
    setMilitares(res.data || []);
  };

  const loadServicios = async () => {
    const res = await http.get("/api/servicios");
    setServicios(res.data || []);
  };

  const loadRealizados = async (id) => {
    if (!id) return setRealizados([]);
    const res = await http.get("/api/servicios-realizados", { params: { militarId: id } });
    setRealizados(res.data || []);
  };

  useEffect(() => {
    (async () => {
      try {
        setLoading(true);
        setError("");
        await Promise.all([loadMilitares(), loadServicios()]);
      } catch (err) {
        const msg =
          err?.response?.data?.error ||
          err?.response?.data?.message ||
          err?.message ||
          "Error al cargar catálogos";
        setError(msg);
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  // Si cambia militarId, cargo su ficha y realizados
  useEffect(() => {
    const m = militares.find((x) => String(x.id) === String(militarId)) || null;
    setMilitarSel(m || null);
    if (m) loadRealizados(m.id);
    else setRealizados([]);
  }, [militarId, militares]);

  // Filtro local de militares
  const militaresFiltrados = useMemo(() => {
    const q = busqueda.trim().toLowerCase();
    if (!q) return militares;
    return militares.filter((m) => {
      const bag = `${m.nombre} ${m.apellido} ${m.dni} ${m.codigoMilitar} ${m.cuerpoNombre} ${m.companiaNombre} ${m.cuartelNombre}`.toLowerCase();
      return bag.includes(q);
    });
  }, [busqueda, militares]);

  // --- HANDLERS ---
  const onSubmitRealizado = async (e) => {
    e.preventDefault();
    if (!militarSel) return;
    if (!form.servicioId || !form.fecha) {
      setError("Seleccioná servicio y fecha.");
      return;
    }
    setSaving(true);
    setError("");
    try {
      await http.post("/api/servicios-realizados", {
        militarId: militarSel.id,
        servicioId: Number(form.servicioId),
        fecha: form.fecha, // yyyy-MM-dd
      });
      setForm({ servicioId: "", fecha: "" });
      await loadRealizados(militarSel.id);
    } catch (err) {
      const msg =
        err?.response?.data?.error ||
        err?.response?.data?.message ||
        err?.message ||
        "No se pudo registrar el servicio realizado.";
      setError(msg);
    } finally {
      setSaving(false);
    }
  };

  if (!isSubo) {
    return (
      <div className="dashboard-container">
        <h2>403 — No tenés permiso</h2>
        <p>Este panel es para Suboficial u Oficial.</p>
      </div>
    );
  }

  if (loading) return <p style={{ padding: 24 }}>Cargando…</p>;

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h2>Panel del Suboficial</h2>
      </div>

      {error && (
        <div style={{ background: "#ffe7e7", border: "1px solid #f5b5b5", color: "#8a1f1f", padding: 12, borderRadius: 8, marginBottom: 16 }}>
          {error}
        </div>
      )}

      <div style={{ display: "grid", gap: 16, gridTemplateColumns: "340px 1fr" }}>
        {/* Columna izquierda: listado de militares */}
        <section style={{ border: "1px solid #eee", borderRadius: 12, padding: 12 }}>
          <h3 style={{ marginTop: 0 }}>Militares</h3>
          <input
            placeholder="Buscar por nombre, DNI, código, cuerpo, compañía, cuartel…"
            value={busqueda}
            onChange={(e) => setBusqueda(e.target.value)}
            style={{ width: "100%", marginBottom: 10 }}
          />
          <div style={{ maxHeight: 420, overflow: "auto" }}>
            {militaresFiltrados.length === 0 && <p style={{ opacity: 0.7 }}>No hay resultados</p>}
            <ul style={{ listStyle: "none", margin: 0, padding: 0 }}>
              {militaresFiltrados.map((m) => {
                const active = String(m.id) === String(militarId);
                return (
                  <li
                    key={m.id}
                    onClick={() => setMilitarId(m.id)}
                    style={{
                      padding: "8px 10px",
                      borderRadius: 8,
                      marginBottom: 6,
                      cursor: "pointer",
                      background: active ? "#eef6ff" : "transparent",
                      border: active ? "1px solid #b3d4ff" : "1px solid #eee",
                    }}
                    title={`${m.nombre} ${m.apellido} — ${m.codigoMilitar || ""}`}
                  >
                    <div style={{ fontWeight: 600 }}>
                      {m.nombre} {m.apellido}
                    </div>
                    <div style={{ fontSize: 12, opacity: 0.75 }}>
                      DNI: {m.dni} • Código: {m.codigoMilitar || "-"}
                    </div>
                    <div style={{ fontSize: 12, opacity: 0.75 }}>
                      {m.cuerpoNombre || "-"} · {m.companiaNombre || "-"} · {m.cuartelNombre || "-"}
                    </div>
                  </li>
                );
              })}
            </ul>
          </div>
        </section>

        {/* Columna derecha: ficha + servicios realizados + alta */}
        <section style={{ border: "1px solid #eee", borderRadius: 12, padding: 12 }}>
          {!militarSel ? (
            <p>Elegí un militar de la lista para ver su detalle.</p>
          ) : (
            <>
              <header style={{ marginBottom: 12 }}>
                <h3 style={{ margin: 0 }}>
                  {militarSel.nombre} {militarSel.apellido}
                </h3>
                <div style={{ fontSize: 13, opacity: 0.8, display: "flex", gap: 12, flexWrap: "wrap" }}>
                  <span><b>Código:</b> {militarSel.codigoMilitar || "-"}</span>
                  <span><b>Cuerpo:</b> {militarSel.cuerpoNombre || "-"}</span>
                  <span><b>Compañía:</b> {militarSel.companiaNombre || "-"}</span>
                  <span><b>Cuartel:</b> {militarSel.cuartelNombre || "-"}</span>
                </div>
              </header>

              <div style={{ display: "grid", gap: 16, gridTemplateColumns: "1fr 380px" }}>
                {/* Tabla de realizados */}
                <div>
                  <h4 style={{ marginTop: 0 }}>Servicios realizados</h4>
                  {realizados.length === 0 ? (
                    <p style={{ opacity: 0.7 }}>Sin registros.</p>
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
                        {realizados.map((r) => (
                          <tr key={r.id} style={{ borderBottom: "1px solid #f4f4f4" }}>
                            <td style={{ padding: "8px 6px", whiteSpace: "nowrap" }}>
                              {r.servicioCodigo}
                            </td>
                            <td style={{ padding: "8px 6px" }}>{r.servicioDescripcion}</td>
                            <td style={{ padding: "8px 6px", whiteSpace: "nowrap" }}>
                              {r.fecha ? new Date(r.fecha).toLocaleDateString() : ""}
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  )}
                </div>

                {/* Alta de realizado */}
                <div style={{ border: "1px solid #eee", borderRadius: 10, padding: 12 }}>
                  <h4 style={{ marginTop: 0 }}>Marcar servicio realizado</h4>
                  <form onSubmit={onSubmitRealizado} style={{ display: "grid", gap: 8 }}>
                    <label>
                      Servicio
                      <select
                        value={form.servicioId}
                        onChange={(e) => setForm((f) => ({ ...f, servicioId: e.target.value }))}
                        style={{ width: "100%" }}
                        required
                      >
                        <option value="">— Elegí un servicio —</option>
                        {servicios.map((s) => (
                          <option key={s.id} value={s.id}>
                            [{s.codigo}] {s.descripcion}
                          </option>
                        ))}
                      </select>
                    </label>

                    <label>
                      Fecha
                      <input
                        type="date"
                        value={form.fecha}
                        onChange={(e) => setForm((f) => ({ ...f, fecha: e.target.value }))}
                        required
                        style={{ width: "100%" }}
                      />
                    </label>

                    <button disabled={saving} style={{ marginTop: 6 }}>
                      {saving ? "Guardando…" : "Marcar realizado"}
                    </button>
                    <small style={{ opacity: 0.7 }}>
                      * No se puede duplicar el mismo servicio en la misma fecha para el mismo militar.
                    </small>
                  </form>
                </div>
              </div>
            </>
          )}
        </section>
      </div>
    </div>
  );
}
