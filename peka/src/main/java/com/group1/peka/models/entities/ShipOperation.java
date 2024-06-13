package com.group1.peka.models.entities;

import java.time.LocalDateTime;

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
@Table(name = "ship operation")
@Getter
@Setter
@NoArgsConstructor
public class ShipOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_operation_id")
    private int shipOperationID;

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "departure_port_id", nullable = false)
    private DeparturePort departurePort;

    @ManyToOne
    @JoinColumn(name = "arrival_port_id", nullable = false)
    private ArrivalPort arrivalPort;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "adult_price", nullable = false)
    private int adultPrice;

    @Column(name = "child_price")
    private int childPrice;    

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;


    public ShipOperation(int shipOperationID, Ship ship, DeparturePort departurePort, ArrivalPort arrivalPort, int duration, int adultPrice, int childPrice, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.shipOperationID = shipOperationID;
        this.ship = ship;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.duration = duration;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

}
