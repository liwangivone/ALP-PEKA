// package com.group1.peka.services;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.group1.peka.dto.user.UserData;
// import com.group1.peka.models.entities.Transaction;
// import com.group1.peka.models.entities.User;
// import com.group1.peka.models.repositories.TransactionRepo;


// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class TransactionService {

//     @Autowired
//     private TransactionRepo transactionRepo;

//     public Transaction createTransaction(UserData user, String virtualAccountNumber, int totalPrice, int passengerQuantity, LocalDateTime transactionTime) {
//         // Implementasi method createTransaction
//         Transaction transaction = new Transaction();
//         transaction.setUser(user);
//         transaction.setVirtualAccountNumber(virtualAccountNumber);
//         transaction.setTotalPrice(totalPrice);
//         transaction.setPassengerQuantity(passengerQuantity);
//         transaction.setTransactionTime(transactionTime);
        
//         // Simpan transaksi ke database
//         // transactionRepository.save(transaction);
        
//         return transaction;
//     }

//     public Optional<Transaction> getTransactionByID(int id) {
//         return transactionRepo.findById(id);
//     }

//     public List<Transaction> getAllTransactionByUser(User user) {
//         return transactionRepo.findByUser(user);
//     }
// }
