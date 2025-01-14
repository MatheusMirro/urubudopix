package com.mirro.urubudopix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirro.urubudopix.exceptions.EmailAlreadyExistsException;
import com.mirro.urubudopix.models.User;
import com.mirro.urubudopix.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok("User Created Successfully " + createdUser.getUsername());
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{userId}/balance")
    public ResponseEntity<String> updateBalance(@PathVariable Long userId, @RequestBody Double amount) {
        try {
            userService.updateBalance(userId, amount);
            return ResponseEntity.ok("Balance updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating balance: " + e.getMessage());
        }
    }
}
