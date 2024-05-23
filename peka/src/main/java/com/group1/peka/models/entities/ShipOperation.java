package com.group1.peka.models.entities;

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
@Table(name = "ship_operation")
@Getter
@Setter
@NoArgsConstructor
public class ShipOperation {

    @Id
    @Column(name = "ship_operation_id")
    private String shipOperationID;

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

    
    public ShipOperation(String shipOperationID, Ship ship, DeparturePort departurePort, ArrivalPort arrivalPort, int duration) {
        this.shipOperationID = shipOperationID;
        this.ship = ship;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.duration = duration;
    }
}
