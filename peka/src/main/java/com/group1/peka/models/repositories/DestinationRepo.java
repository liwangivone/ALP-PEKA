package com.group1.peka.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Destination;

public interface DestinationRepo extends CrudRepository<Destination, Integer> {

    Optional<Destination> findByDestinationID(int destinationID);

    Optional<Destination> findByDestinationName(String destinationName);
    
}
