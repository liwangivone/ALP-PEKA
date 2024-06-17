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
@Table(name = "ship schedule")
@Getter
@Setter
@NoArgsConstructor
public class ShipSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_schedule_id")
    private int shipScheduleID;

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Origin origin;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Column(name = "adult_price", nullable = false)
    private int adultPrice;

    @Column(name = "child_price")
    private int childPrice;    

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;


    public ShipSchedule(int shipScheduleID, Ship ship, Origin origin, Destination destination, int adultPrice, int childPrice, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.shipScheduleID = shipScheduleID;
        this.ship = ship;
        this.origin = origin;
        this.destination = destination;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
