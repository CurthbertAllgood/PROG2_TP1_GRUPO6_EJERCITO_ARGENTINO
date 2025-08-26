// src/pages/Dashboard.jsx
import { useAuth } from "../auth/UserContext";
import SoldierDashboard from "./SoldierDashboard";
import SuboficialDashboard from "./SuboficialDashboard";
import OfficialDashboard from "./OfficialDashboard";

export default function Dashboard() {
  const { user } = useAuth();
  if (!user) return <p style={{ padding: 24 }}>No hay sesión activa.</p>;

  const viewsByRole = {
    OFICIAL: OfficialDashboard,
    SUBOFICIAL: SuboficialDashboard,
    SOLDADO: SoldierDashboard,
  };

  const View = viewsByRole[user.role] ?? (() => <div style={{padding:24}}>Rol desconocido</div>);
  return <View />;
}
