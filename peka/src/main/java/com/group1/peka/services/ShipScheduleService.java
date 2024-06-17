package com.group1.peka.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.Destination;
import com.group1.peka.models.entities.Origin;
import com.group1.peka.models.entities.Ship;
import com.group1.peka.models.entities.ShipSchedule;
import com.group1.peka.models.repositories.DestinationRepo;
import com.group1.peka.models.repositories.OriginRepo;
import com.group1.peka.models.repositories.ShipRepo;
import com.group1.peka.models.repositories.ShipScheduleRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShipScheduleService {

    @Autowired
    private ShipScheduleRepo shipScheduleRepo;

    @Autowired
    private ShipRepo shipRepo;

    @Autowired
    private OriginRepo originRepo;

    @Autowired
    private DestinationRepo destinationRepo;


    public ShipSchedule createShipSchedule(int shipID, int originID, int destinationID, int adultPrice, int childPrice, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        try {
            Optional<Ship> shipOpt = shipRepo.findByShipID(shipID);
            if (!shipOpt.isPresent()) {
                throw new Exception("Ship not found");
            }

            Optional<Origin> originOpt = originRepo.findByOriginID(originID);
            if (!originOpt.isPresent()) {
                throw new Exception("Origin not found");
            }

            Optional<Destination> destinationOpt = destinationRepo.findByDestinationID(destinationID);
            if (!destinationOpt.isPresent()) {
                throw new Exception("Destination not found");
            }

            if (arrivalTime.isBefore(departureTime)) {
                throw new Exception("Arrival time cannot be before departure time");
            }

            ShipSchedule shipSchedule = new ShipSchedule();
            shipSchedule.setShip(shipOpt.get());
            shipSchedule.setOrigin(originOpt.get());
            shipSchedule.setDestination(destinationOpt.get());
            shipSchedule.setAdultPrice(adultPrice);
            shipSchedule.setChildPrice(childPrice);
            shipSchedule.setDepartureTime(departureTime);
            shipSchedule.setArrivalTime(arrivalTime);

            return shipScheduleRepo.save(shipSchedule);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating ship schedule", e);
        }
    }

    public ShipSchedule updateShipSchedule(int shipScheduleID, int shipID, int originID, int destinationID, int adultPrice, int childPrice, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        try {
            Optional<ShipSchedule> shipScheduleOpt = shipScheduleRepo.findByShipScheduleID(shipScheduleID);
            if (!shipScheduleOpt.isPresent()) {
                throw new Exception("Ship schedule not found");
            }

            Optional<Ship> shipOpt = shipRepo.findByShipID(shipID);
            if (!shipOpt.isPresent()) {
                throw new Exception("Ship not found");
            }

            Optional<Origin> originOpt = originRepo.findByOriginID(originID);
            if (!originOpt.isPresent()) {
                throw new Exception("Origin not found");
            }

            Optional<Destination> destinationOpt = destinationRepo.findByDestinationID(destinationID);
            if (!destinationOpt.isPresent()) {
                throw new Exception("Destination not found");
            }

            if (arrivalTime.isBefore(departureTime)) {
                throw new Exception("Arrival time cannot be before departure time");
            }

            ShipSchedule shipSchedule = shipScheduleOpt.get();
            shipSchedule.setShip(shipOpt.get());
            shipSchedule.setOrigin(originOpt.get());
            shipSchedule.setDestination(destinationOpt.get());
            shipSchedule.setAdultPrice(adultPrice);
            shipSchedule.setChildPrice(childPrice);
            shipSchedule.setDepartureTime(departureTime);
            shipSchedule.setArrivalTime(arrivalTime);

            return shipScheduleRepo.save(shipSchedule);
        } catch (Exception ee) {
            ee.printStackTrace();
            throw new RuntimeException("Error updating ship schedule", ee);
        }
    }

    public void deleteShipSchedule(int shipScheduleID) throws Exception {
        if (!shipScheduleRepo.existsByShipScheduleID(shipScheduleID)) {
            throw new Exception("Ship schedule not found");
        }
        shipScheduleRepo.deleteByShipScheduleID(shipScheduleID);
    }

    public Iterable<ShipSchedule> getAllShipSchedules() {
        return shipScheduleRepo.findAll();
    }

    public Optional<ShipSchedule> getShipScheduleByID(int shipScheduleID) {
        return shipScheduleRepo.findByShipScheduleID(shipScheduleID);
    }
}