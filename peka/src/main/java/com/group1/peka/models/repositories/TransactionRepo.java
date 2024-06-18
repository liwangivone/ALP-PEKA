package com.group1.peka.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Transaction;
import com.group1.peka.models.entities.User;

import java.util.List;
import java.util.Optional;


public interface TransactionRepo extends CrudRepository<Transaction, Integer> {

    List<Transaction> findByUser(User user);  
    
    Optional<Transaction> findByTransactionID(int transactionID);

}
