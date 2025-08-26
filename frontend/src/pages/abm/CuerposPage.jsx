import React, { useEffect, useState } from "react";
import { http } from "../../auth/http";
import { useAuth } from "../../auth/UserContext";

export default function CuerposPage() {
  const { hasRole } = useAuth();
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ codigo: "", denominacion: "" });
  const [edit, setEdit] = useState(null);
  const [error, setError] = useState("");

  const canDelete = hasRole("OFICIAL"); // sólo oficial puede borrar

  const load = async () => {
    const res = await http.get("/api/cuerpos");
    setItems(res.data ?? []);
  };

  useEffect(() => { load().catch(console.error); }, []);

  const submit = async (e) => {
    e.preventDefault(); setError("");
    try {
      if (edit) {
        await http.put(`/api/cuerpos/${edit.id}`, form);
      } else {
        await http.post("/api/cuerpos", form);
      }
      setForm({ codigo: "", denominacion: "" });
      setEdit(null);
      await load();
    } catch (err) {
      const msg = err?.response?.data?.error || err?.response?.data?.message || err?.message || "Error al guardar";
      setError(msg);
    }
  };

  const onEdit = (it) => {
    setEdit(it);
    setForm({ codigo: it.codigo, denominacion: it.denominacion });
  };

  const onCancel = () => {
    setEdit(null);
    setForm({ codigo: "", denominacion: "" });
    setError("");
  };

  const onDelete = async (id) => {
    if (!canDelete) return;
    if (!window.confirm("¿Eliminar cuerpo?")) return;
    try { await http.delete(`/api/cuerpos/${id}`); await load(); }
    catch (err) {
      const msg = err?.response?.data?.error || err?.response?.data?.message || err?.message || "No se pudo eliminar";
      setError(msg);
    }
  };

  return (
    <div style={{ padding: 24 }}>
      <h2>Cuerpos — ABM</h2>

      <form onSubmit={submit} style={{ display: "grid", gap: 8, maxWidth: 440, marginBottom: 16 }}>
        <input
          placeholder="Código"
          value={form.codigo}
          onChange={e => setForm({ ...form, codigo: e.target.value })}
          required
        />
        <input
          placeholder="Denominación"
          value={form.denominacion}
          onChange={e => setForm({ ...form, denominacion: e.target.value })}
          required
        />
        <div style={{ display: "flex", gap: 8 }}>
          <button type="submit">{edit ? "Guardar cambios" : "Crear"}</button>
          {edit && <button type="button" onClick={onCancel}>Cancelar</button>}
        </div>
        {error && <p style={{ color: "crimson" }}>{error}</p>}
      </form>

      <table style={{ width: "100%", borderCollapse: "collapse" }}>
        <thead>
          <tr style={{ textAlign: "left", borderBottom: "1px solid #eee" }}>
            <th style={{ padding: 8 }}>Código</th>
            <th style={{ padding: 8 }}>Denominación</th>
            <th style={{ padding: 8, width: 200 }}></th>
          </tr>
        </thead>
        <tbody>
          {items.map(it => (
            <tr key={it.id} style={{ borderBottom: "1px solid #f4f4f4" }}>
              <td style={{ padding: 8 }}>{it.codigo}</td>
              <td style={{ padding: 8 }}>{it.denominacion}</td>
              <td style={{ padding: 8 }}>
                <button onClick={() => onEdit(it)}>Editar</button>{" "}
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
