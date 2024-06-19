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

    public Ship createShip(String shipName, int capacity, String status) {
        Optional<Ship> nameCheck = shipRepo.findByShipName(shipName);

        if (nameCheck.isPresent()) {
            return null;
        }

        Ship ship = new Ship('0', shipName, capacity, status);

        return shipRepo.save(ship);
    }

    public Optional<Ship> getShipByID(int shipID) {
        return shipRepo.findByShipID(shipID);
    }

    public Optional<Ship> getShipByShipName(String shipName) {
        return shipRepo.findByShipName(shipName);
    }

    public Iterable<Ship> getAllShips() {
        return shipRepo.findAll();
    }

    public Ship updateShip(Ship ship) {
        return shipRepo.save(ship);
    }

    public boolean deleteShipByIdAndName(int shipID, String shipName) {
        Optional<Ship> ship = shipRepo.findById(shipID);
        if (ship.isPresent() && ship.get().getShipName().equals(shipName)) {
            shipRepo.delete(ship.get());
            return true;
        }
        return false;
    }
}
