package com.group1.peka.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ship")
@Getter
@Setter
@NoArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_id")
    private int shipID;

    @Column(name = "ship_name", nullable = false)
    private String shipName;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    
    @Column(name = "status", nullable = false)
    private String status;


    public Ship(int shipID, String shipName, int capacity, String status) {
        this.shipID = shipID;
        this.shipName = shipName;
        this.capacity = capacity;
        this.status = status;
    }
}
