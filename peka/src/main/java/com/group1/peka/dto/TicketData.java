package com.group1.peka.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketData {

    private int ticketID;

    private int price;

    private String passengerName;

    private String passengerType;    


    public TicketData(int ticketID, int price, String passengerName, String passengerType) {
        this.ticketID = ticketID;
        this.price = price;
        this.passengerName = passengerName;
        this.passengerType = passengerType;
    }
}