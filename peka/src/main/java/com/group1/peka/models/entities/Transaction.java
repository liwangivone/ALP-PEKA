package com.group1.peka.models.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private String transactionID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "va_number", nullable = false)
    private String vaNumber;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Column(name = "passenger_quantity", nullable = false)
    private int passengerQuantity;

    @Column(name = "transaction_time", nullable = false)
    private LocalDateTime transactionTime;


    public Transaction(String transactionID, User user, String vaNumber, int totalPrice, int passengerQuantity, LocalDateTime transactionTime) {
        this.transactionID = transactionID;
        this.user = user;
        this.vaNumber = vaNumber;
        this.totalPrice = totalPrice;
        this.passengerQuantity = passengerQuantity;
        this.transactionTime = transactionTime;
    }    
}
