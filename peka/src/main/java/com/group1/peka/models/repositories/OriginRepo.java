package com.group1.peka.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Origin;

public interface OriginRepo extends CrudRepository<Origin, Integer> {

    Optional<Origin> findByOriginID(int originID);
    
}
