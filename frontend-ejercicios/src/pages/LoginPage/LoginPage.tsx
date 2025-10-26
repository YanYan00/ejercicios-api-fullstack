import React from "react";
import LoginForm from "../../components/LoginForm/LoginForm";
import "./LoginPage.css"
const LoginPage: React.FC = () =>{
    return(

        <div className="login-page">
            <h1>Inicio de Sesión</h1>
            {<LoginForm></LoginForm>}
        </div>
    )
}
export default LoginPage;

