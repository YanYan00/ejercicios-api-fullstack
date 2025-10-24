package com.exercises.ejercicios_api.service;

import com.exercises.ejercicios_api.dtos.AuthRequest;
import com.exercises.ejercicios_api.dtos.AuthResponse;
import com.exercises.ejercicios_api.model.UserModel;
import com.exercises.ejercicios_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserModel getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void newUser(UserModel user) {
        Optional<UserModel> res = userRepository.findByEmail(user.getEmail());
        if(res.isPresent()){
            throw new IllegalStateException("Ya existe un usuario con el email " + user.getEmail());
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest authRequest) {
        Optional<UserModel> res = userRepository.findByEmail(authRequest.getEmail());
        if(res.isPresent()){
            UserModel user = res.get();
            boolean passwordMatch = passwordEncoder.matches(
                    authRequest.getPassword(),
                    user.getPassword()
            );
            if(passwordMatch){
                String jwt = jwtService.generateToken(user);
                AuthResponse response = new AuthResponse();
                response.setToken(jwt);
                response.setEmail(user.getEmail());
                response.setType_user(user.getType_user().getName_type());
                System.out.println("Login exitoso!");
                return response;
            }
            else{
                throw new IllegalStateException("Contraseña incorrecta");
            }
        }
        else {
            throw new IllegalStateException("Credenciales invalidas");
        }
    }
}
