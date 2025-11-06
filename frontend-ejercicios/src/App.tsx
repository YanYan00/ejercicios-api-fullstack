import React from 'react';
// 🚨 CORRECCIÓN: Importar BrowserRouter, Routes y Route directamente.
import { BrowserRouter, Routes, Route } from "react-router-dom"; 
// Importa tus páginas
import LoginPage from './pages/LoginPage/LoginPage';
import RegisterPage from './pages/RegisterPage/RegisterPage';
import Navbar from './components/Navbar/Navbar';
// Suponiendo que tienes una página de bienvenida o dashboard
// import DashboardPage from './pages/DashboardPage'; 

const App: React.FC = () => {
    return (
        <>
            <Navbar></Navbar>
            <BrowserRouter>
                {/* ... */}
                <Routes>
                    
                    {/* Ruta pública para el Login */}
                    <Route path="/login" element={<LoginPage />} />
                    
                    {/* Agrega la ruta raíz */}
                    <Route path='/register' element={<RegisterPage></RegisterPage>}></Route>
                    {/* <Route path="/" element={<DashboardPage />} /> */}

                </Routes>
            </BrowserRouter>
        </>
        
    );
};

export default App;
