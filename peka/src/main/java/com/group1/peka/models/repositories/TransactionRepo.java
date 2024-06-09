package com.group1.peka.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Transaction;
import com.group1.peka.models.entities.User;

import java.util.List;


public interface TransactionRepo extends CrudRepository<Transaction, String> {

    List<Transaction> findByUser(User user);    
}
