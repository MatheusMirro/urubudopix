package com.mirro.urubudopix.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirro.urubudopix.services.BetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class BetController {

    @Autowired
    private BetService betService;

    @PostMapping("/{userId}/bet")
    public ResponseEntity<String> makeBet(@PathVariable Long userId,
            @RequestParam("winner") Boolean winner,
            @RequestBody Double amount) {

        try {
            betService.makeBet(userId, amount, winner);
        } catch (Exception e) {
            throw new RuntimeException("A aposta falhou! " + e.getMessage());
        }
        return ResponseEntity.ok("Aposta realizada!");
    }
}
