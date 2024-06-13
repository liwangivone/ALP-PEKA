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

    public Ship createShip(int shipID, String name, int capacity) {
        Ship ship = new Ship(shipID, name, capacity);
        return shipRepo.save(ship);
    }

    public Optional<Ship> getShipByID(int shipID) {
        return shipRepo.findById(shipID);
    }

    public Iterable<Ship> getAllShips() {
        return shipRepo.findAll();
    }

    public void reduceCapacity(int shipID, int quantity) throws Exception {
        Optional<Ship> shipOpt = shipRepo.findById(shipID);
        if (!shipOpt.isPresent()) {
            throw new Exception("Ship not found");
        }
        
        Ship ship = shipOpt.get();
        if (ship.getCapacity() < quantity) {
            throw new Exception("Not enough capacity");
        }
        
        ship.setCapacity(ship.getCapacity() - quantity);
        shipRepo.save(ship);
    }
    
    
}
