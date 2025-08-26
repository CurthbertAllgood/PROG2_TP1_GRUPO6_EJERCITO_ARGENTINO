import React, { useEffect, useState } from "react";
import { http } from "../../auth/http";
import { useAuth } from "../../auth/UserContext";

export default function CuartelesPage() {
  const { hasRole } = useAuth();
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ codigo: "", nombre: "", ubicacion: "" });
  const [edit, setEdit] = useState(null);
  const [error, setError] = useState("");

  const canDelete = hasRole("OFICIAL");

  const load = async () => {
    const res = await http.get("/api/cuarteles");
    setItems(res.data ?? []);
  };

  useEffect(() => { load().catch(console.error); }, []);

  const submit = async (e) => {
    e.preventDefault(); setError("");
    try {
      if (edit) {
        await http.put(`/api/cuarteles/${edit.id}`, form);
      } else {
        await http.post("/api/cuarteles", form);
      }
      setForm({ codigo: "", nombre: "", ubicacion: "" });
      setEdit(null);
      await load();
    } catch (err) {
      const msg = err?.response?.data?.error || err?.response?.data?.message || err?.message || "Error al guardar";
      setError(msg);
    }
  };

  const onEdit = (it) => {
    setEdit(it);
    setForm({ codigo: it.codigo ?? "", nombre: it.nombre ?? "", ubicacion: it.ubicacion ?? "" });
  };

  const onCancel = () => {
    setEdit(null);
    setForm({ codigo: "", nombre: "", ubicacion: "" });
    setError("");
  };

  const onDelete = async (id) => {
    if (!canDelete) return;
    if (!window.confirm("¿Eliminar cuartel?")) return;
    try { await http.delete(`/api/cuarteles/${id}`); await load(); }
    catch (err) {
      const msg = err?.response?.data?.error || err?.response?.data?.message || err?.message || "No se pudo eliminar";
      setError(msg);
    }
  };

  return (
    <div style={{ padding: 24 }}>
      <h2>Cuarteles — ABM</h2>

      <form onSubmit={submit} style={{ display: "grid", gap: 8, maxWidth: 520, marginBottom: 16 }}>
        <input
          placeholder="Código"
          value={form.codigo}
          onChange={e => setForm({ ...form, codigo: e.target.value })}
          required
        />
        <input
          placeholder="Nombre"
          value={form.nombre}
          onChange={e => setForm({ ...form, nombre: e.target.value })}
          required
        />
        <input
          placeholder="Ubicación"
          value={form.ubicacion}
          onChange={e => setForm({ ...form, ubicacion: e.target.value })}
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
            <th style={{ padding: 8 }}>Nombre</th>
            <th style={{ padding: 8 }}>Ubicación</th>
            <th style={{ padding: 8 }}>Compañías</th>
            <th style={{ padding: 8, width: 200 }}></th>
          </tr>
        </thead>
        <tbody>
          {items.map(it => (
            <tr key={it.id} style={{ borderBottom: "1px solid #f4f4f4" }}>
              <td style={{ padding: 8 }}>{it.codigo}</td>
              <td style={{ padding: 8 }}>{it.nombre}</td>
              <td style={{ padding: 8 }}>{it.ubicacion}</td>
              <td style={{ padding: 8 }}>{(it.companiaNombres ?? []).join(", ")}</td>
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
        * La asociación Cuartel–Compañías no se edita aquí. Si querés, luego agregamos un editor de relaciones.
      </small>
    </div>
  );
}
