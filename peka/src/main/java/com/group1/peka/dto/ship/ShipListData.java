package com.group1.peka.dto.ship;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipListData {

    private List<?> ship = new ArrayList<>();

    public ShipListData(List<?> ship) {
        this.ship = ship;
    }  
}
