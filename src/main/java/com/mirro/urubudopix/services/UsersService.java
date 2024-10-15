package com.mirro.urubudopix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirro.urubudopix.exceptions.EmailAlreadyExistsException;
import com.mirro.urubudopix.models.User;
import com.mirro.urubudopix.repositories.UserRepository;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + user.getEmail());
        }
        return userRepository.save(user);
    }
}