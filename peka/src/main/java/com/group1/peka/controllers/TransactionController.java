package com.group1.peka.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.models.entities.Transaction;
import com.group1.peka.models.entities.User;
import com.group1.peka.services.TransactionService;
import com.group1.peka.services.UserService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    private UserService userService;

    @PostMapping("/user/{id}")
    public ResponseEntity<ResponseData<Transaction>> createTransaction(
            @RequestParam String email,
            @RequestParam int transactionID,
            @RequestParam String vaNumber,
            @RequestParam int totalPrice,
            @RequestParam int passengerQuantity) {
        ResponseData<Transaction> responseData = new ResponseData<>();

        Optional<User> user = userService.getUserByEmail(email);
        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        Transaction transaction = transactionService.saveTransaction(
                transactionID, user.get(), vaNumber, totalPrice, passengerQuantity, LocalDateTime.now());

        responseData.setStatus(true);
        responseData.setPayload(transaction);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseData<List<Transaction>>> getAllTransactionByUser(
            @RequestParam String email) {
        ResponseData<List<Transaction>> responseData = new ResponseData<>();

        Optional<User> user = userService.getUserByEmail(email);
        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        List<Transaction> transaction = transactionService.getAllTransactionByUser(user.get());

        responseData.setStatus(true);
        responseData.setPayload(transaction);
        return ResponseEntity.ok(responseData);
    }
}
