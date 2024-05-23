package com.group1.peka.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "destination")
@Getter
@Setter
@NoArgsConstructor
public class Destination {

    @Id
    @Column(name = "destination_id")
    private String destinationID;

    @Column(name = "name", nullable = false)
    private String name;


    public Destination(String destinationID, String name) {
        this.destinationID = destinationID;
        this.name = name;
    }
}
