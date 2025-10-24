package com.exercises.ejercicios_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean 1: Define el codificador de contraseñas.
     * Es necesario para inyectar en UserService (para hashear y comparar contraseñas).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean 2: Define el administrador de autenticación.
     * Es necesario si usas el AuthenticationManager en tu lógica de login (aunque aquí
     * lo hacemos manualmente en UserService, este bean puede ser útil más adelante).
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Bean 3: Define la cadena de filtros de seguridad (Las reglas de acceso HTTP).
     * Esta es la pieza clave que protege rutas y permite login/registro.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilita CSRF (Cross-Site Request Forgery) para APIs REST
                .csrf(csrf -> csrf.disable())

                // 2. Configura las reglas de autorización para cada endpoint
                .authorizeHttpRequests(auth -> auth
                        // Rutas PÚBLICAS: Acceso permitido para login y registro (AuthController)
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll() // POST para Registro
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()    // POST para Login

                        // Rutas Protegidas: Cualquier otra petición requiere autenticación (token)
                        .anyRequest().authenticated()
                )

                // 3. Configura la gestión de sesiones como SIN ESTADO (STATELASS)
                // Esto es FUNDAMENTAL para usar JWT
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // NOTA: Una vez que el login funcione, aquí deberás agregar el filtro JWT
        // para validar el token en cada petición protegida.

        return http.build();
    }
}
