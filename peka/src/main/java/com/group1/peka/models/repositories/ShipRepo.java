package com.group1.peka.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Ship;

public interface ShipRepo extends CrudRepository<Ship, Integer> {

    Optional<Ship> findByShipID(int shipID);

}
