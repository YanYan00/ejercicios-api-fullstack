import React, { useState } from "react";

const DashboardPage : React.FC = () =>{
    const [perfil, setPerfil] = useState({});
    return(
        <>
            <div>
                
            </div>
            <div className="profileButtons">
                <button>Editar</button>
                <button>Cancelar</button>
            </div>
        </>
    )
}

export default DashboardPage;