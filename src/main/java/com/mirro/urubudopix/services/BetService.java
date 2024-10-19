package com.mirro.urubudopix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mirro.urubudopix.exceptions.InsufficientFundsException;
import com.mirro.urubudopix.models.HouseAccount;
import com.mirro.urubudopix.models.User;
import com.mirro.urubudopix.repositories.HouseAccountRepository;
import com.mirro.urubudopix.repositories.UserRepository;

@Service
public class BetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HouseAccountService houseAccountService;

    @Autowired
    private HouseAccountRepository houseAccountRepository;

    public ResponseEntity<String> makeBet(Long userId, Double amount, Boolean winner) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Double wallet = user.getBalance();

        if (wallet == 0.0 || wallet < amount) {
            throw new InsufficientFundsException("Saldo insuficiente, por favor, deposite.");
        }
        user.setBalance(user.getBalance() - amount);
        adjustBalanceAfterBet(user, amount, winner);

        return ResponseEntity.ok("Aposta Finalizada");
    }

    public void adjustBalanceAfterBet(User user, Double amount, Boolean winner) {
        if (winner) {
            user.setBalance(user.getBalance() + (amount * 2));
        } else {
            houseAccountService.receiveMoney(1, amount);
        }
        userRepository.save(user);
    }
}
