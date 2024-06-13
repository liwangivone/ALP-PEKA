package com.group1.peka.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.ShipOperation;

public interface ShipOperationRepo extends CrudRepository<ShipOperation, Integer> {
    
}
