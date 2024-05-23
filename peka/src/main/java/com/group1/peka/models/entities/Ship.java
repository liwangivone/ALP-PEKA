package com.group1.peka.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Column(name = "ship_id", nullable = false)
    private String shipID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "ship_type", nullable = false)
    private ShipType shipType;


    public enum ShipType {
        SMALL,
        LARGE
    }

    public Ship(String shipID, String name, int capacity, ShipType shipType) {
        this.shipID = shipID;
        this.name = name;
        this.capacity = capacity;
        this.shipType = shipType;
    }
}
