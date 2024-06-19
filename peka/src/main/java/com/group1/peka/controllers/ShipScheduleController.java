package com.group1.peka.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        
        // Get all IDs for validation
        List<Integer> allShipIDs = shipService.getAllShips().stream().map(Ship::getShipID).collect(Collectors.toList());
        List<Integer> allOriginIDs = originService.getAllOrigins().stream().map(Origin::getOriginID).collect(Collectors.toList());
        List<Integer> allDestinationIDs = destinationService.getAllDestinations().stream().map(Destination::getDestinationID).collect(Collectors.toList());

        // Validate the provided IDs
        if (!allShipIDs.contains(shipID)) {
            responseData.setStatus(false);
            responseData.getMessages().add("Ship with ID " + shipID + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        if (!allOriginIDs.contains(originID)) {
            responseData.setStatus(false);
            responseData.getMessages().add("Origin with ID " + originID + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        if (!allDestinationIDs.contains(destinationID)) {
            responseData.setStatus(false);
            responseData.getMessages().add("Destination with ID " + destinationID + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

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

    @GetMapping("/all")
    public ResponseEntity<ResponseData<Iterable<ShipSchedule>>> getAllShipScheduleS() {
        ResponseData<Iterable<ShipSchedule>> responseData = new ResponseData<>();
        try {
            Iterable<ShipSchedule> shipSchedule = shipScheduleService.getAllShipSchedule();

                responseData.setStatus(true);
                responseData.setPayload(shipSchedule);
                return ResponseEntity.ok(responseData);
    
            
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add("Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllShipSchedulesById(@PathVariable int id) {
        ResponseData<Object> responseData = new ResponseData<>();
        try {
            Optional<ShipSchedule> shipSchedule = shipScheduleService.getShipScheduleByID(id);
            if(shipSchedule.isPresent()) {
                responseData.setStatus(true);
                responseData.setPayload(shipSchedule.get());
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setStatus(true);
                responseData.getMessages().add("Ship Schedule Not Found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
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
    @DeleteMapping("/id")
    public ResponseEntity<ResponseData<ShipSchedule>> delete(
            @RequestParam int shipScheduleID) {

        ResponseData<ShipSchedule> responseData = new ResponseData<>();
        
        boolean isDeleted = shipScheduleService.deleteShipSchedule(shipScheduleID);

        if (!isDeleted) {
            responseData.setStatus(false);
            responseData.getMessages().add("Ship schedule not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);

        }

        responseData.setStatus(true);
        responseData.getMessages().add("The ship schedule with ID " + shipScheduleID + " is successfully deleted");

        return ResponseEntity.ok(responseData);
}
}
        
   