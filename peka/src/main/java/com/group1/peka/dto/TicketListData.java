package com.group1.peka.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketListData {

    private List<?> ticket  = new ArrayList<>();
    
    private String message;

    public TicketListData(List<?> ticket) {
        this.ticket = ticket;
    }
}
