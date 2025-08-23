import React, { useEffect, useState } from "react";
import { http } from "../auth/http";
import { useAuth } from "../auth/UserContext";

export default function ServicesPages() {
  const { hasRole } = useAuth();
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ codigo: "", descripcion: "" });
  const [error, setError] = useState("");

  const load = async () => setItems((await http.get("/api/servicios")).data);

  useEffect(() => { load(); }, []);

  const create = async (e) => {
    e.preventDefault(); setError("");
    try { await http.post("/api/servicios", form); setForm({ codigo:"", descripcion:"" }); load(); }
    catch (err) { setError(err?.response?.data?.error || err.message); }
  };

  return (
    <div style={{ padding: 24 }}>
      <h2>Servicios</h2>
      {hasRole("SUBOFICIAL","OFICIAL") && (
        <form onSubmit={create} style={{display:"grid", gap:8, maxWidth:420}}>
          <input placeholder="Código" value={form.codigo} onChange={e=>setForm({...form, codigo:e.target.value})}/>
          <input placeholder="Descripción" value={form.descripcion} onChange={e=>setForm({...form, descripcion:e.target.value})}/>
          <button>Crear</button>
        </form>
      )}
      {error && <p style={{color:"crimson"}}>{error}</p>}
      <ul>{items.map(s => <li key={s.id}>{s.codigo} — {s.descripcion}</li>)}</ul>
    </div>
  );
}
