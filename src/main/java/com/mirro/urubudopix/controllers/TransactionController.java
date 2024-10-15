package com.mirro.urubudopix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirro.urubudopix.models.Transaction;
import com.mirro.urubudopix.services.TransactionsService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody Transaction transaction) {
        try {
            transaction.setType("DEPOSIT");
            Transaction createTransaction = transactionsService.createTransaction(transaction);
            return ResponseEntity.ok(createTransaction.getAmount() + "Deposit Succesful: ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@RequestBody Transaction transaction) {
        try {
            transaction.setType("WITHDRAWAL");
            Transaction createTransaction = transactionsService.createTransaction(transaction);
            return ResponseEntity.ok(createTransaction.getAmount() + "Withdrawal Succesful: ");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
