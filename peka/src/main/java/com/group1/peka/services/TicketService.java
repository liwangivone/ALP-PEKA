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

    public Ticket createTicket(ShipSchedule shipSchedule, Transaction transaction, int price, String passengerName, String passengerType) {
        Ticket ticket = new Ticket(
            '0', 
            shipSchedule, 
            transaction, 
            price,  
            passengerName, 
            passengerType);

        return ticketRepo.save(ticket);
    }

    public Ticket updateTicket(Ticket existingTicket, ShipSchedule shipSchedule, Transaction transaction, int price, String passengerName, String passengerType) {
        existingTicket.setShipSchedule(shipSchedule);
        existingTicket.setTransaction(transaction);
        existingTicket.setPrice(price);
        existingTicket.setPassengerName(passengerName);
        existingTicket.setPassengerType(passengerType);

        return ticketRepo.save(existingTicket);
    }

    public Optional<Ticket> getTicketByID(int ticketID) {
        return ticketRepo.findById(ticketID);
    }
    
    public void deleteTicketByID(int ticketID) {
        ticketRepo.deleteById(ticketID);
    }
}
