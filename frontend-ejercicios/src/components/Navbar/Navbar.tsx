import type React from "react";

const Navbar: React.FC = () => {
    return(
        <div className="navbar">
            <button className="home-button">Home</button>
            <button className="appointment-button">Citas</button>
            <button className="notification-button">Notificaciones</button>
            <button className="profile-button">Perfil</button>
            <button className="logout-button">Cerrar Sesion</button>
        </div>
    )
}

export default Navbar;