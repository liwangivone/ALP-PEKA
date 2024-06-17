package com.group1.peka.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.Transaction;
import com.group1.peka.models.entities.User;
import com.group1.peka.models.repositories.TransactionRepo;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction saveTransaction(int transactionID, User user, String vaNumber, int totalPrice, int passengerQuantity, LocalDateTime transactionTime) {
        Transaction transaction = new Transaction(
            transactionID, 
            user, 
            vaNumber, 
            totalPrice, 
            passengerQuantity, 
            transactionTime);

        return transactionRepo.save(transaction);

    }

    public Optional<Transaction> getTransactionByID(int id) {
        return transactionRepo.findById(id);
    }

    public List<Transaction> getAllTransactionByUser(User user) {
        return transactionRepo.findByUser(user);
    }
}
