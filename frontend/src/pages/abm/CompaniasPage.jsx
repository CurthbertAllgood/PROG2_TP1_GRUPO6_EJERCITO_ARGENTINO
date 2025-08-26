import React, { useEffect, useState } from "react";
import { http } from "../../auth/http";
import { useAuth } from "../../auth/UserContext";

export default function CompaniasPage() {
  const { hasRole } = useAuth();
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ numero: "", actividadPrincipal: "" });
  const [edit, setEdit] = useState(null);
  const [error, setError] = useState("");

  const canDelete = hasRole("OFICIAL");

  const load = async () => {
    const res = await http.get("/api/companias");
    setItems(res.data ?? []);
  };

  useEffect(() => { load().catch(console.error); }, []);

  const submit = async (e) => {
    e.preventDefault(); setError("");
    const payload = { numero: form.numero ? Number(form.numero) : null, actividadPrincipal: form.actividadPrincipal };
    try {
      if (edit) {
        await http.put(`/api/companias/${edit.id}`, payload);
      } else {
        await http.post("/api/companias", payload);
      }
      setForm({ numero: "", actividadPrincipal: "" });
      setEdit(null);
      await load();
    } catch (err) {
      const msg = err?.response?.data?.error || err?.response?.data?.message || err?.message || "Error al guardar";
      setError(msg);
    }
  };

  const onEdit = (it) => {
    setEdit(it);
    setForm({ numero: it.numero ?? "", actividadPrincipal: it.actividadPrincipal ?? "" });
  };

  const onCancel = () => {
    setEdit(null);
    setForm({ numero: "", actividadPrincipal: "" });
    setError("");
  };

  const onDelete = async (id) => {
    if (!canDelete) return;
    if (!window.confirm("¿Eliminar compañía?")) return;
    try { await http.delete(`/api/companias/${id}`); await load(); }
    catch (err) {
      const msg = err?.response?.data?.error || err?.response?.data?.message || err?.message || "No se pudo eliminar";
      setError(msg);
    }
  };

  return (
    <div style={{ padding: 24 }}>
      <h2>Compañías — ABM</h2>

      <form onSubmit={submit} style={{ display: "grid", gap: 8, maxWidth: 480, marginBottom: 16 }}>
        <input
          placeholder="Número"
          type="number"
          value={form.numero}
          onChange={e => setForm({ ...form, numero: e.target.value })}
          required
        />
        <input
          placeholder="Actividad principal"
          value={form.actividadPrincipal}
          onChange={e => setForm({ ...form, actividadPrincipal: e.target.value })}
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
            <th style={{ padding: 8 }}>#</th>
            <th style={{ padding: 8 }}>Actividad principal</th>
            <th style={{ padding: 8 }}>Cuarteles</th>
            <th style={{ padding: 8, width: 200 }}></th>
          </tr>
        </thead>
        <tbody>
          {items.map(it => (
            <tr key={it.id} style={{ borderBottom: "1px solid #f4f4f4" }}>
              <td style={{ padding: 8 }}>{it.numero}</td>
              <td style={{ padding: 8 }}>{it.actividadPrincipal}</td>
              <td style={{ padding: 8 }}>{(it.cuartelNombres ?? []).join(", ")}</td>
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

      <small style={{ opacity: 0.7 }}>
        * La asociación Compañía–Cuartel no se edita aquí (simple para el TP). Si querés, luego agregamos multiselect.
      </small>
    </div>
  );
}
