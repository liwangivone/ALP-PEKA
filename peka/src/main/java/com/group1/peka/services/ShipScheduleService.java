package com.group1.peka.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.Destination;
import com.group1.peka.models.entities.Origin;
import com.group1.peka.models.entities.Ship;
import com.group1.peka.models.entities.ShipSchedule;
import com.group1.peka.models.repositories.ShipScheduleRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShipScheduleService {

    @Autowired
    private ShipScheduleRepo shipScheduleRepo;

    public ShipSchedule createShipSchedule(Ship ship, Origin origin, Destination destination, LocalDateTime departureTime, LocalDateTime arrivalTime, int adultPrice, int childPrice ) {
        ShipSchedule shipSchedule = new ShipSchedule(
            '0',
            ship,
            origin,
            destination,
            departureTime,
            arrivalTime,
            adultPrice,
            childPrice);
        
        return shipScheduleRepo.save(shipSchedule);
    }

    public ShipSchedule updateShipSchedule(ShipSchedule existingShipSchedule, Ship ship, Origin origin, Destination destination, LocalDateTime departureTime, LocalDateTime arrivalTime, int adultPrice, int childPrice) {
        existingShipSchedule.setShip(ship);
        existingShipSchedule.setOrigin(origin);
        existingShipSchedule.setDestination(destination);
        existingShipSchedule.setDepartureTime(departureTime);
        existingShipSchedule.setArrivalTime(arrivalTime);
        existingShipSchedule.setAdultPrice(adultPrice);
        existingShipSchedule.setChildPrice(childPrice);

        return shipScheduleRepo.save(existingShipSchedule);
    }

    public Optional<ShipSchedule> getShipScheduleByID(int shipScheduleID) {
        return shipScheduleRepo.findById(shipScheduleID);
    }

    public void deleteShipSchedule(int shipScheduleID) {
        shipScheduleRepo.deleteById(shipScheduleID);
    }
}