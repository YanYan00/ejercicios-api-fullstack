package com.exercises.ejercicios_api.service;

import com.exercises.ejercicios_api.dtos.AuthRequest;
import com.exercises.ejercicios_api.dtos.AuthRequestRegister;
import com.exercises.ejercicios_api.dtos.AuthResponse;
import com.exercises.ejercicios_api.model.TypeUserModel;
import com.exercises.ejercicios_api.model.UserModel;
import com.exercises.ejercicios_api.repository.TypeUserRepository;
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
    private final TypeUserRepository typeUserRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, TypeUserRepository typeUserRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.typeUserRepository = typeUserRepository;
    }

    public UserModel getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void newUser(AuthRequestRegister register) {
        Optional<UserModel> res = userRepository.findByEmail(register.getEmail());
        if(res.isPresent()){
            throw new IllegalStateException("Ya existe un usuario con el email " + register.getEmail());
        }
        if(!register.getPassword().equals(register.getConfirmPassword())){
            throw new RuntimeException("Las contrasenas no coinciden");
        }
        String encodedPassword = passwordEncoder.encode(register.getPassword());
        register.setPassword(encodedPassword);
        UserModel user = new UserModel();
        user.setEmail(register.getEmail());
        user.setPassword(encodedPassword);
        user.setCreated_at(java.time.LocalDateTime.now());
        TypeUserModel typeUser = typeUserRepository.findById(1L)
                        .orElseThrow(()-> new IllegalStateException("No existe el tipo de usuario"));
        user.setType_user(typeUser);
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
