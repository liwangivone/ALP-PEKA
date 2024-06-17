package com.group1.peka.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.ShipSchedule;
import com.group1.peka.models.entities.Ticket;
import com.group1.peka.models.entities.Transaction;
import com.group1.peka.models.repositories.TicketRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    public Ticket saveTicket(int ticketID, ShipSchedule shipOperation, Transaction transaction, int price, String passengerName, String passengerType) {
        Ticket ticket = new Ticket(
            ticketID, 
            shipOperation, 
            transaction, 
            price,  
            passengerName, 
            passengerType);

        return ticketRepo.save(ticket);
    }

    public Optional<Ticket> getTicketByID(int ticketID) {
        return ticketRepo.findById(ticketID);
    }
    
    public void deleteTicketByID(int id) {
        ticketRepo.deleteById(id);
    }
}
