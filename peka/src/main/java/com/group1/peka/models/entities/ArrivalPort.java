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
@Table(name = "arrival port")
@Getter
@Setter
@NoArgsConstructor
public class ArrivalPort {

    @Id
    @Column(name = "port_id", length = 10)
    private String portID;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Column(name = "name", nullable = false)
    private String name;


    public ArrivalPort(String portID, Destination destination, String name) {
        this.portID = portID;
        this.destination = destination;
        this.name = name;
    }
}
