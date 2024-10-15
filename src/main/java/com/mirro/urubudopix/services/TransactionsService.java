package com.mirro.urubudopix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirro.urubudopix.models.Transaction;
import com.mirro.urubudopix.models.User;
import com.mirro.urubudopix.repositories.TransactionsRepository;
import com.mirro.urubudopix.repositories.UserRepository;

@Service
public class TransactionsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    public List<Transaction> findByType(String type) {
        return transactionsRepository.findByType(type);
    }

    public Transaction createTransaction(Transaction transaction) {
        User user = userRepository.findById(transaction.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (transaction.getType().equals("DEPOSIT")) {
            user.setBalance(user.getBalance() + transaction.getAmount());
        } else if (transaction.getType().equals("WITHDRAWAL")) {
            user.setBalance(user.getBalance() - transaction.getAmount());
        }
        userRepository.save(user);
        return transactionsRepository.save(transaction);

    }

}
