package com.group1.peka.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Destination;

public interface DestinationRepo extends CrudRepository<Destination, Integer> {
    
}
