package com.group1.peka.dto.destination;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DestinationData {

    private int destinationID;

    private String destinationName;


    public DestinationData(int destinationID, String destinationName) {
        this.destinationID = destinationID;
        this.destinationName = destinationName;
    }
}
