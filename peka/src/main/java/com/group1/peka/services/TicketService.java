package com.group1.peka.services;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.ShipOperation;
import com.group1.peka.models.entities.Ticket;
import com.group1.peka.models.entities.Transaction;
import com.group1.peka.models.entities.Ticket.PassengerType;
import com.group1.peka.models.repositories.TicketRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    public Ticket saveTicket(String ticketID, ShipOperation shipOperation, Transaction transaction, int price, LocalDateTime departurTime, LocalDateTime arrivalTime, String passengerName, PassengerType passengerType) {
        Ticket ticket = new Ticket(
            ticketID, 
            shipOperation, 
            transaction, price, 
            departurTime, 
            arrivalTime, 
            passengerName, 
            passengerType);

        return ticketRepo.save(ticket);
    }

    public Optional<Ticket> getTicketByID(String ticketID) {
        return ticketRepo.findById(ticketID);
    }

    public List<Ticket> getAllTicketByShipOperation(ShipOperation shipOperation) {
        return ticketRepo.findByShipOperation(shipOperation);
    }

    public void deleteTicketByID(String id) {
        ticketRepo.deleteById(id);
    }
}
