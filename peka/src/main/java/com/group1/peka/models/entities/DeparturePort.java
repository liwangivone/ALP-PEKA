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
@Table(name = "departure_port")
@Getter
@Setter
@NoArgsConstructor
public class DeparturePort {

    @Id
    @Column(name = "port_id")
    private String portID;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Column(name = "name", nullable = false)
    private String name;


    public DeparturePort(String portID, Destination destination, String name) {
        this.portID = portID;
        this.destination = destination;
        this.name = name;
    }
}

