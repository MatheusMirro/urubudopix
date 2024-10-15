package com.mirro.urubudopix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirro.urubudopix.models.User;
import com.mirro.urubudopix.services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.findByEmail(user.getEmail()).map(u -> "Login successful")
                .orElse("Invalid credentials");
    }
}
