package com.group1.peka.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.DeparturePort;

public interface DeparturePortRepo extends CrudRepository<DeparturePort, Integer>{
    
}
