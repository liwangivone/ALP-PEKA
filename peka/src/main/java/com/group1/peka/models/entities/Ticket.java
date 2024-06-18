package com.group1.peka.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int ticketID;

    @ManyToOne
    @JoinColumn(name = "ship_schedule_id", nullable = false)
    private ShipSchedule shipSchedule;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "passenger_type", nullable = false)
    private String passengerType;

    public Ticket(int ticketID, ShipSchedule shipSchedule, Transaction transaction, int price, String passenggerName, String passengerType) {
        this.ticketID = ticketID;
        this.shipSchedule = shipSchedule;
        this.transaction = transaction;
        this.price = price;
        this.passengerName = passenggerName;
        this.passengerType = passengerType;
    }
}
