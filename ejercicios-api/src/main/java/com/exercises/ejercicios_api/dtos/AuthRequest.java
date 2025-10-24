package com.exercises.ejercicios_api.dtos;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
