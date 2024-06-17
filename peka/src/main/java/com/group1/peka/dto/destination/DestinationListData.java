package com.group1.peka.dto.destination;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinationListData {

    private List<?> destination = new ArrayList<>();

    public DestinationListData(List<?> destination) {
        this.destination = destination;
    }
}
