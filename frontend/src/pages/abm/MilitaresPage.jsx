import React, { useEffect, useState } from "react";
import { http } from "../../auth/http";
import { useAuth } from "../../auth/UserContext";

const emptyForm = {
  nombre: "",
  apellido: "",
  dni: "",
  codigoMilitar: "",
  tipo: "SOLDADO",      // SOLDADO | SUBOFICIAL | OFICIAL
  cuerpoId: "",
  companiaId: "",
  cuartelId: "",
};

export default function MilitaresPage() {
  const { hasRole } = useAuth();
  const [items, setItems] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [error, setError] = useState("");
  const [msg, setMsg] = useState("");

  // catálogos
  const [cuerpos, setCuerpos] = useState([]);
  const [companias, setCompanias] = useState([]);
  const [cuarteles, setCuarteles] = useState([]);

  const canDelete = hasRole("OFICIAL"); // DELETE solo Oficial, POST/GET también Suboficial

  const loadAll = async () => {
    setError(""); setMsg("");
    const [mil, cup, com, cua] = await Promise.all([
      http.get("/api/militares"),
      http.get("/api/cuerpos"),
      http.get("/api/companias"),
      http.get("/api/cuarteles"),
    ]);
    setItems(mil.data ?? []);
    setCuerpos(cup.data ?? []);
    setCompanias(com.data ?? []);
    setCuarteles(cua.data ?? []);
  };

  useEffect(() => { loadAll().catch(e => setError(e.message)); }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    setError(""); setMsg("");
    try {
      const payload = {
        ...form,
        cuerpoId: form.cuerpoId ? Number(form.cuerpoId) : null,
        companiaId: form.companiaId ? Number(form.companiaId) : null,
        cuartelId: form.cuartelId ? Number(form.cuartelId) : null,
      };
      await http.post("/api/militares", payload);
      setForm(emptyForm);
      setMsg("Militar creado correctamente.");
      await loadAll();
    } catch (err) {
      const m = err?.response?.data?.error || err?.response?.data?.message || err?.message || "Error al guardar";
      setError(m);
    }
  };

  const onDelete = async (id) => {
    if (!canDelete) return;
    if (!window.confirm("¿Eliminar militar? Esta acción no puede deshacerse.")) return;
    try {
      await http.delete(`/api/militares/${id}`);
      setMsg("Militar eliminado.");
      await loadAll();
    } catch (err) {
      const m = err?.response?.data?.error || err?.response?.data?.message || err?.message || "No se pudo eliminar";
      setError(m);
    }
  };

  return (
    <div style={{ padding: 24 }}>
      <h2>Militares — ABM</h2>

      {msg && <div style={{ background:"#e7ffef", border:"1px solid #b7e3c6", color:"#215b36", padding:10, borderRadius:8, marginBottom:12 }}>{msg}</div>}
      {error && <div style={{ background:"#ffe7e7", border:"1px solid #e3b7b7", color:"#5b2121", padding:10, borderRadius:8, marginBottom:12 }}>{error}</div>}

      <form onSubmit={onSubmit} style={{ display:"grid", gap:8, maxWidth:640, marginBottom:16 }}>
        <div style={{ display:"grid", gap:8, gridTemplateColumns:"1fr 1fr" }}>
          <input placeholder="Nombre" value={form.nombre} onChange={e=>setForm({ ...form, nombre:e.target.value })} required />
          <input placeholder="Apellido" value={form.apellido} onChange={e=>setForm({ ...form, apellido:e.target.value })} required />
        </div>

        <div style={{ display:"grid", gap:8, gridTemplateColumns:"1fr 1fr" }}>
          <input placeholder="DNI" value={form.dni} onChange={e=>setForm({ ...form, dni:e.target.value })} required />
          <input placeholder="Código Militar (único)" value={form.codigoMilitar} onChange={e=>setForm({ ...form, codigoMilitar:e.target.value })} required />
        </div>

        <div style={{ display:"grid", gap:8, gridTemplateColumns:"1fr 1fr 1fr 1fr" }}>
          <select value={form.tipo} onChange={e=>setForm({ ...form, tipo:e.target.value })}>
            <option value="SOLDADO">Soldado</option>
            <option value="SUBOFICIAL">Suboficial</option>
            <option value="OFICIAL">Oficial</option>
          </select>

          <select value={form.cuerpoId} onChange={e=>setForm({ ...form, cuerpoId:e.target.value })} required>
            <option value="">— Cuerpo —</option>
            {cuerpos.map(c => <option key={c.id} value={c.id}>[{c.codigo}] {c.denominacion}</option>)}
          </select>

          <select value={form.companiaId} onChange={e=>setForm({ ...form, companiaId:e.target.value })} required>
            <option value="">— Compañía —</option>
            {companias.map(c => <option key={c.id} value={c.id}>#{c.numero} — {c.actividadPrincipal}</option>)}
          </select>

          <select value={form.cuartelId} onChange={e=>setForm({ ...form, cuartelId:e.target.value })} required>
            <option value="">— Cuartel —</option>
            {cuarteles.map(c => <option key={c.id} value={c.id}>[{c.codigo}] {c.nombre}</option>)}
          </select>
        </div>

        <div>
          <button type="submit">Crear militar</button>
        </div>

        <small style={{ opacity: 0.75 }}>
          Tip: luego de crear el militar, registrá el usuario con <code>/auth/register</code> usando el <b>id</b> devuelto como <code>personaId</code>.
        </small>
      </form>

      <table style={{ width:"100%", borderCollapse:"collapse" }}>
        <thead>
          <tr style={{ textAlign:"left", borderBottom:"1px solid #eee" }}>
            <th style={{ padding:8 }}>ID</th>
            <th style={{ padding:8 }}>Nombre</th>
            <th style={{ padding:8 }}>DNI</th>
            <th style={{ padding:8 }}>Código</th>
            <th style={{ padding:8 }}>Cuerpo</th>
            <th style={{ padding:8 }}>Compañía</th>
            <th style={{ padding:8 }}>Cuartel</th>
            <th style={{ padding:8, width:160 }}></th>
          </tr>
        </thead>
        <tbody>
          {items.map(it => (
            <tr key={it.id} style={{ borderBottom:"1px solid #f4f4f4" }}>
              <td style={{ padding:8 }}>{it.id}</td>
              <td style={{ padding:8 }}>{it.nombre} {it.apellido}</td>
              <td style={{ padding:8 }}>{it.dni}</td>
              <td style={{ padding:8 }}>{it.codigoMilitar}</td>
              <td style={{ padding:8 }}>{it.cuerpoNombre}</td>
              <td style={{ padding:8 }}>{it.companiaNombre}</td>
              <td style={{ padding:8 }}>{it.cuartelNombre}</td>
              <td style={{ padding:8 }}>
                <button onClick={() => onDelete(it.id)} disabled={!canDelete} title={canDelete ? "" : "Sólo Oficial"}>
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

    </div>
  );
}
