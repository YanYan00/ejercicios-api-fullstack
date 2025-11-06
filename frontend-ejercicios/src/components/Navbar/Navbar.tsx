import type React from "react";
import { AuthService } from "../../services/AuthService";
import { useNavigate } from "react-router-dom";

const Navbar: React.FC = () => {
    const navigate = useNavigate();
    const logout = () =>{
        AuthService.logout();
        navigate('/login', {replace: true});
    }
    return(
        <div className="navbar">
            <button className="home-button">Home</button>
            <button className="appointment-button">Citas</button>
            <button className="notification-button">Notificaciones</button>
            <button className="profile-button">Perfil</button>
            <button className="logout-button" onClick={logout}>Cerrar Sesion</button>
        </div>
    )
}

export default Navbar;