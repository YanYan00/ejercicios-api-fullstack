package com.exercises.ejercicios_api.dtos;

import lombok.Data;

@Data
public class AuthRequestRegister{
    private String email;
    private String password;
    private String confirmPassword;
}
