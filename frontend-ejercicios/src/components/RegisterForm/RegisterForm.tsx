import type React from "react";
import { useState } from "react";
import { AuthService } from "../../services/AuthService";


const RegisterForm : React.FC = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const handleSubmit = async(e:React.FormEvent) =>{
        e.preventDefault();
        setLoading(true);
        try {
            await AuthService.register({email,password,confirmPassword})
            const loginData = await AuthService.login({email,password})
            console.log(loginData);
        } catch (error) {
            console.log(error);
        }
    }
    return(
        <>
            <form onSubmit={handleSubmit}>
                <div className="form-input-register">
                    <label  className="form-label-register">Email </label>
                    <input type="email" className="form-control-register" value={email} onChange={(e) => setEmail(e.target.value)} required placeholder="Ingresa tu email..."></input>
                </div>
                <div className="form-input-register">
                    <label  className="form-label-register">Password </label>
                    <input type="password" className="form-control-register" value={password} onChange={(e) => setPassword(e.target.value)} required placeholder="Ingresa tu contrasena..."></input>
                </div>
                <div className="form-input-register">
                    <label  className="form-label-register">Confirm Password </label>
                    <input type="password" className="form-control-register" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required placeholder="Confirma tu contrasena..."></input>
                </div>
                <button type="submit" disabled={loading} className="send-button-register">{loading ? 'Validando...' : 'Entrar'}</button>
            </form>
        </>
    )
}
export default RegisterForm;