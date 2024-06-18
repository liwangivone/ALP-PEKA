package com.group1.peka.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.dto.user.UserData;
import com.group1.peka.models.entities.Transaction;
import com.group1.peka.models.entities.User;
import com.group1.peka.services.TransactionService;
import com.group1.peka.services.UserService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/id")
    public ResponseEntity<ResponseData<Transaction>> createTransaction(
            @RequestParam String email,
            @RequestParam int totalPrice,
            @RequestParam int passengerQuantity) {

        ResponseData<Transaction> responseData = new ResponseData<>();

        Optional<User> user = userService.getUserByEmail(email);
        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        // Generate random 10-digit number for vaNumber
        String vaNumber = generateRandomVaNumber();

        Transaction transaction = transactionService.createTransaction(
                user.get(),
                vaNumber,
                totalPrice,
                passengerQuantity,
                LocalDateTime.now());

        responseData.setStatus(true);
        responseData.setPayload(transaction);
        responseData.getMessages().add("Transaction created successfully");

        return ResponseEntity.ok(responseData);
    }

    private String generateRandomVaNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000000) + 100000000; // Generate 9-digit random number
        return String.valueOf(randomNumber);
    }

    @GetMapping("/user/id")
    public ResponseEntity<ResponseData<List<UserData>>> getAllTransactionByUser(
            @RequestParam String email) {
        ResponseData<List<UserData>> responseData = new ResponseData<>();

        Optional<User> user = userService.getUserByEmail(email);
        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        // Mengambil transaksi dari user
        List<Transaction> transactions = transactionService.getAllTransactionByUser(user.get());

        // Mengubah transaksi menjadi UserData
        List<UserData> userDataList = convertToUserDataList(transactions);

        responseData.setStatus(true);
        responseData.setPayload(userDataList);
        return ResponseEntity.ok(responseData);
    }

    private List<UserData> convertToUserDataList(List<Transaction> transactions) {
        // Membuat list UserData dari transaksi
        List<UserData> userDataList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            UserData userData = new UserData(transaction.getUser().getEmail(), transaction.getUser().getName());
            userDataList.add(userData);
        }
        return userDataList;
    }
}
