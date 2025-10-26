import apiClient from "../api/ApiClient";

interface LoginCredentials{
    email:string;
    password:string;
}
interface LoginResponse{
    token: string;
    email: string ;
    type_user: string ;
}
interface RegisterCredentials{
    email: string ;
    password:string;
    confirmPassword:string;
}
export const AuthService ={
    login: async (data: LoginCredentials): Promise<LoginResponse> =>{
        const response = await apiClient.post<LoginResponse>('/auth/login',data);
        localStorage.setItem('jwtToken',response.data.token);
        return response.data;
    },
    register: async (data:RegisterCredentials) =>{
        const response = await apiClient.post('/auth/register',data)
        return response.data;
    }
}