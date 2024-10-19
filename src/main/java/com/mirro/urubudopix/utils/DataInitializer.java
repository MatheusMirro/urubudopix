package com.mirro.urubudopix.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirro.urubudopix.models.HouseAccount;
import com.mirro.urubudopix.repositories.HouseAccountRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private HouseAccountRepository houseAccountRepository;

    @PostConstruct
    public void init() {
        // Verifica se já existe uma conta da casa
        if (houseAccountRepository.count() == 0) {
            HouseAccount houseAccount = new HouseAccount();
            houseAccount.setBalance(0.0);
            houseAccountRepository.save(houseAccount);
        }
    }
}
