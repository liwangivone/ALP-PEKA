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
@Table(name = "origin")
@Getter
@Setter
@NoArgsConstructor
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origin_id")
    private int originID;

    @Column(name = "origin_name", nullable = false)
    private String originName;


    public Origin(int originID, String originName) {
        this.originID = originID;
        this.originName = originName;
    }  
}
