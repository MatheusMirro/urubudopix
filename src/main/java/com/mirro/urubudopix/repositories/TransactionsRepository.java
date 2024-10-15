package com.mirro.urubudopix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirro.urubudopix.models.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByType(String type);

}
