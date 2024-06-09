package com.group1.peka.dto;

import java.time.LocalDateTime;

import com.group1.peka.models.entities.Ticket.PassengerType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketData {

    private String ticketID;

    private int price;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String passengerName;

    private PassengerType passengerType;    


    public TicketData(String ticketID, int price, LocalDateTime departureTime, LocalDateTime arrivalTime, String passengerName, PassengerType passengerType) {
        this.ticketID = ticketID;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.passengerName = passengerName;
        this.passengerType = passengerType;
    }
}