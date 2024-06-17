package com.group1.peka.dto.ship;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipData {

    private int shipID;

    private String shipName;

    private int capacity;

    private String status;


    public ShipData(int shipID, String shipName, int capacity, String status) {
        this.shipID = shipID;
        this.shipName = shipName;
        this.capacity = capacity;
        this.status = status;
    }    
}
