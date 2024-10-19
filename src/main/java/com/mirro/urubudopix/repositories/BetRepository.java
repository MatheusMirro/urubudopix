package com.mirro.urubudopix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirro.urubudopix.models.Bet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
}
