package com.group1.peka.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Ship;
import com.group1.peka.models.entities.ShipOperation;
import com.group1.peka.models.entities.Ticket;

public interface ShipRepo extends CrudRepository<Ship, String> {

    Optional<Ship>findByShipID(String shipID);

    List<Ticket> findByShipOperation(ShipOperation shipOperation);
    
}
