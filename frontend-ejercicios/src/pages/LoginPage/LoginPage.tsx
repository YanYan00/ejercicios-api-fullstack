import React, { useEffect } from "react";
import LoginForm from "../../components/LoginForm/LoginForm";
import "./LoginPage.css"
import { useNavigate } from "react-router-dom";
const LoginPage: React.FC = () =>{
    const navigate = useNavigate();
    useEffect (() =>{
        const token = localStorage.getItem('jwtToken');
        if(token){
            navigate('/dashboard', {replace: true});
        }
    },[navigate]);
    return(
        <div className="login-page">
            <h1>Inicio de Sesión</h1>
            {<LoginForm></LoginForm>}
        </div>
    )
}
export default LoginPage;

