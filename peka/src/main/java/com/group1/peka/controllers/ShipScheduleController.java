package com.group1.peka.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.models.entities.Destination;
import com.group1.peka.models.entities.Origin;
import com.group1.peka.models.entities.Ship;
import com.group1.peka.models.entities.ShipSchedule;
import com.group1.peka.services.DestinationService;
import com.group1.peka.services.OriginService;
import com.group1.peka.services.ShipScheduleService;
import com.group1.peka.services.ShipService;

@RestController
@RequestMapping("/api/shipSchedule")
public class ShipScheduleController {

    @Autowired 
    private ShipScheduleService shipScheduleService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private OriginService originService;

    @Autowired 
    private DestinationService destinationService;

    @PostMapping("/admin/add")
    public ResponseEntity<ResponseData<ShipSchedule>> createShipSchedule(
            @RequestParam int shipID,
            @RequestParam int originID,
            @RequestParam int destinationID,
            @RequestParam String departureTime,
            @RequestParam String arrivalTime,
            @RequestParam int adultPrice,
            @RequestParam int childPrice) {

        ResponseData<ShipSchedule> responseData = new ResponseData<>();
        
        // Fetch the related entities
        Optional<Ship> ship = shipService.getShipByID(shipID);
        Optional<Origin> origin = originService.getOriginByID(originID);
        Optional<Destination> destination = destinationService.getDestinationByID(destinationID);

        // Check if all entities are present
        if (ship.isPresent() && origin.isPresent() && destination.isPresent()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime departureDateTime = LocalDateTime.parse(departureTime, formatter);
                LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalTime, formatter);

                // Create the ship schedule using the service method
                ShipSchedule shipSchedule = shipScheduleService.createShipSchedule(
                    ship.get(), 
                    origin.get(), 
                    destination.get(), 
                    departureDateTime, 
                    arrivalDateTime, 
                    adultPrice, 
                    childPrice
                );

                responseData.setStatus(true);
                responseData.setPayload(shipSchedule);
                responseData.getMessages().add("Ship schedule successfully created");

                return ResponseEntity.ok(responseData);
            } catch (DateTimeParseException e) {
                responseData.setStatus(false);
                responseData.getMessages().add("Error parsing date/time: " + e.getMessage());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        } else {
            responseData.setStatus(false);
            responseData.getMessages().add("One or more entities not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<ShipSchedule>> getAllShipScheduleByID(@PathVariable("id") int shipScheduleID) {
        ResponseData<ShipSchedule> responseData = new ResponseData<>();
        try {
            Optional<ShipSchedule> shipSchedule = shipScheduleService.getShipScheduleByID(shipScheduleID);

            if (shipSchedule.isPresent()) {
                responseData.setStatus(true);
                responseData.setPayload(shipSchedule.get());
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setStatus(false);
                responseData.getMessages().add("Ship schedule not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add("Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ResponseData<ShipSchedule>> updateShipScheduleByID(
            @PathVariable("id") int shipScheduleID,
            @RequestParam int shipID,
            @RequestParam int originID,
            @RequestParam int destinationID,
            @RequestParam String departureTime,
            @RequestParam String arrivalTime,
            @RequestParam int adultPrice,
            @RequestParam int childPrice) {

        ResponseData<ShipSchedule> responseData = new ResponseData<>();
        
        // Fetch the related entities
        Optional<ShipSchedule> existingShipSchedule = shipScheduleService.getShipScheduleByID(shipScheduleID);
        Optional<Ship> ship = shipService.getShipByID(shipID);
        Optional<Origin> origin = originService.getOriginByID(originID);
        Optional<Destination> destination = destinationService.getDestinationByID(destinationID);

        // Check if all entities are present
        if (existingShipSchedule.isPresent() && ship.isPresent() && origin.isPresent() && destination.isPresent()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime departureDateTime = LocalDateTime.parse(departureTime, formatter);
                LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalTime, formatter);

                // Update the ship schedule using the service method
                ShipSchedule shipSchedule = shipScheduleService.updateShipSchedule(
                    existingShipSchedule.get(), 
                    ship.get(), 
                    origin.get(), 
                    destination.get(), 
                    departureDateTime, 
                    arrivalDateTime, 
                    adultPrice, 
                    childPrice
                );

                responseData.setStatus(true);
                responseData.setPayload(shipSchedule);
                responseData.getMessages().add("Ship schedule successfully updated");

                return ResponseEntity.ok(responseData);
            } catch (DateTimeParseException e) {
                responseData.setStatus(false);
                responseData.getMessages().add("Error parsing date/time: " + e.getMessage());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        } else {
            responseData.setStatus(false);
            responseData.getMessages().add("One or more entities not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    // Method to delete ShipSchedule by ID
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<ResponseData<ShipSchedule>> deleteShipScheduleByID(@PathVariable("id") int shipScheduleID) {
        ResponseData<ShipSchedule> responseData = new ResponseData<>();
        Optional<ShipSchedule> shipSchedule = shipScheduleService.getShipScheduleByID(shipScheduleID);

        if (shipSchedule.isPresent()) {
            shipScheduleService.deleteShipSchedule(shipScheduleID);
            responseData.setStatus(true);
            responseData.getMessages().add("Ship schedule successfully deleted");

            return ResponseEntity.ok(responseData);
        } else {
            responseData.setStatus(false);
            responseData.getMessages().add("Ship schedule not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }
}
        
   