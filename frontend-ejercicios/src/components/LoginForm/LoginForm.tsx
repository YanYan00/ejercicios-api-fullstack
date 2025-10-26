import type React from "react";
import "./LoginForm.css"
import { useState } from "react";
import { AuthService } from "../../services/AuthService";

const LoginForm: React.FC = () =>{
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e:React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        try {
            const data = await AuthService.login({email,password});
            console.log(data);
        } catch (error) {
            console.log(error);
        }
        
    }    
    return(
        <>
            <form onSubmit={handleSubmit}>
                <div className="form-input">
                    <label  className="form-label">Correo </label>
                    <input type="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} required placeholder="Ingresa tu email..."></input>
                </div>
                <div className="form-input">
                    <label  className="form-label">Password </label>
                    <input type="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} required placeholder="Ingresa tu contrasena..."></input>
                </div>
                <button type="submit" disabled={loading} className="send-button">{loading ? 'Validando...' : 'Entrar'}</button>
            </form>
                
        </>
        
    )
}
export default LoginForm;