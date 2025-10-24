package com.exercises.ejercicios_api.dtos;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String email;
    private String type_user;
}
