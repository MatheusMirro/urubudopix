package com.mirro.urubudopix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirro.urubudopix.models.HouseAccount;

@Repository
public interface HouseAccountRepository extends JpaRepository<HouseAccount, Integer> {
}