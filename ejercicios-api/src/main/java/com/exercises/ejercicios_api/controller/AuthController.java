package com.exercises.ejercicios_api.controller;

import com.exercises.ejercicios_api.dtos.AuthRequest;
import com.exercises.ejercicios_api.dtos.AuthResponse;
import com.exercises.ejercicios_api.model.UserModel;
import com.exercises.ejercicios_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {
    private UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest request){
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public void createUser(@RequestBody UserModel user){
        this.userService.newUser(user);
    }
}

