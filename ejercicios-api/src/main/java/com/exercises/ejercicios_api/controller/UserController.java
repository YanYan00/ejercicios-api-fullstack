package com.exercises.ejercicios_api.controller;

import com.exercises.ejercicios_api.model.UserModel;
import com.exercises.ejercicios_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping( "/{id}")
    public UserModel getUserId(@PathVariable Long id){
        return this.userService.getUserById(id);
    }
}
