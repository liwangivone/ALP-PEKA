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
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    @Id
    @Column(name = "ticket_id", nullable = false)
    private String ticketID;

    @ManyToOne
    @JoinColumn(name = "ship_operation_id", nullable = false)
    private ShipOperation shipOperationID;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transactionID;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "passenger_name", nullable = false)
    private String passenggerName;


    public Ticket(String ticketID, ShipOperation shipOperationID, Transaction transactionID, int price, LocalDateTime departureTime, LocalDateTime arrivalTime, String passenggerName) {
        this.ticketID = ticketID;
        this.shipOperationID = shipOperationID;
        this.transactionID = transactionID;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.passenggerName = passenggerName;
    }    
}
