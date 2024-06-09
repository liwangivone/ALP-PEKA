package com.group1.peka.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.Ship;
import com.group1.peka.models.repositories.ShipRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShipService {

    @Autowired
    private ShipRepo shipRepo;

    public Ship createShip(String shipID, String name, int capacity, Ship.ShipType shipType) {
        Ship ship = new Ship(shipID, name, capacity, shipType);
        return shipRepo.save(ship);
    }

    public Optional<Ship> getShipByID(String shipID) {
        return shipRepo.findById(shipID);
    }

    public Iterable<Ship> getAllShips() {
        return shipRepo.findAll();
    }  
}
