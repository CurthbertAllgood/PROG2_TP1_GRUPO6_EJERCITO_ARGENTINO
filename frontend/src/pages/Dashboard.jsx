import { useAuth } from "../auth/UserContext";
import { useNavigate } from "react-router-dom";
import SoldierDashboard from "./SoldierDashboard";
import SuboficialDashboard from "./SuboficialDashboard";
import OfficialDashboard from "./OfficialDashboard";


export default function Dashboard() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  if (!user) return <p>No hay sesión activa.</p>;

  const handleLogout = () => {
    logout();          // 👈 en lugar de setUser(null)
    navigate("/");
  };

  // Mapeo por rol para evitar ifs con nombres distintos
const viewsByRole = {
  OFICIAL: OfficialDashboard,
  SUBOFICIAL: SuboficialDashboard,
  SOLDADO: SoldierDashboard,
};


  const View = viewsByRole[user.role] ?? (() => <div>Rol desconocido</div>);

  return (
    <div>
      <h2>Bienvenido, {user.username}</h2>
      <button onClick={handleLogout}>Cerrar sesión</button>
      <View />
    </div>
  );
}
