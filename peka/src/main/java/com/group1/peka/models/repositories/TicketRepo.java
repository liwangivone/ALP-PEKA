package com.group1.peka.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.ShipOperation;
import com.group1.peka.models.entities.Ticket;

public interface TicketRepo extends CrudRepository<Ticket, String> {

    Optional<Ticket> findByTicketID(String ticketID);

    List<Ticket> findByShipOperation(ShipOperation shipOperation);
    
}
