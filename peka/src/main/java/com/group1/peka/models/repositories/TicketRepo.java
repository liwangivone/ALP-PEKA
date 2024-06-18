package com.group1.peka.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Ticket;

public interface TicketRepo extends CrudRepository<Ticket, Integer> {

    Optional<Ticket> findByTicketID(int ticketID);

    void deleteByTicketID(int ticketID);
    
}
