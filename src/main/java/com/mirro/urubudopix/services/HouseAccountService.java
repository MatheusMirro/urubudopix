package com.mirro.urubudopix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirro.urubudopix.models.HouseAccount;
import com.mirro.urubudopix.repositories.HouseAccountRepository;

@Service
public class HouseAccountService {

    @Autowired
    private HouseAccountRepository houseAccountRepository;

    public void receiveMoney(int houseId, Double amount) {

        HouseAccount houseAccount = houseAccountRepository.findById(houseId)
                .orElseThrow(() -> new RuntimeException("House not found"));
        houseAccount.setBalance(houseAccount.getBalance() + amount);
        houseAccountRepository.save(houseAccount);
    }
}
