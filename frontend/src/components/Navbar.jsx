import { Link } from "react-router-dom";
import { useAuth } from "../auth/UserContext";
import "../assets/styles/Navbar.css";

export default function Navbar() {
  const { user, logout } = useAuth();

  return (
    <nav className="navbar">
      <Link to="/">Inicio</Link>
      <div className="navbar__right">
        {user ? (
          <>
            <span className="navbar__user">
              {user.username} — {user.role}
            </span>
            <button className="btn btn-ghost" onClick={logout}>
              Salir
            </button>
          </>
        ) : (
          <Link to="/login">Login</Link>
        )}
      </div>
    </nav>
  );
}
