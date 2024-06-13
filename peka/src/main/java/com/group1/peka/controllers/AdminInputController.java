package com.group1.peka.controllers;

import javax.sound.sampled.Port;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.models.entities.Destination;
import com.group1.peka.models.entities.Ship;
import com.group1.peka.models.entities.ShipOperation;
import com.group1.peka.services.ArrivalPortService;
import com.group1.peka.services.DeparturePortService;
import com.group1.peka.services.DestinationService;
import com.group1.peka.services.ShipOperationService;
import com.group1.peka.services.ShipService;

@RestController
@RequestMapping("/api/adminInput")
public class AdminInputController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private ShipOperationService shipOperationService;

    @Autowired
    private DeparturePortService departurePortService;

    @Autowired
    private ArrivalPortService arrivalPortService;


    @PostMapping("/destination")
    public ResponseEntity<Destination> createDestination(
        @RequestBody Destination destination) {
        Destination newDestination = destinationService.createDestination(destination);
        return ResponseEntity.ok(newDestination);
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable int id, @RequestBody Destination destination) {
        Destination updatedDestination = destinationService.updateDestination(id, destination);
        return ResponseEntity.ok(updatedDestination);
    }

    @DeleteMapping("/destination/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable int id) {
        destinationService.deleteDestination(id);
        return ResponseEntity.noContent().build();
    }

    // // Ship endpoints
    // @PostMapping("/ships")
    // public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
    //     Ship newShip = shipService.createShip(ship);
    //     return ResponseEntity.ok(newShip);
    // }

    // @PutMapping("/ships/{id}")
    // public ResponseEntity<Ship> updateShip(@PathVariable int id, @RequestBody Ship ship) {
    //     Ship updatedShip = shipService.updateShip(id, ship);
    //     return ResponseEntity.ok(updatedShip);
    // }

    // @DeleteMapping("/ships/{id}")
    // public ResponseEntity<Void> deleteShip(@PathVariable int id) {
    //     shipService.deleteShip(id);
    //     return ResponseEntity.noContent().build();
    // }

    // // Ship operation endpoints
    // @PostMapping("/ship-operations")
    // public ResponseEntity<ShipOperation> createShipOperation(@RequestBody ShipOperation shipOperation) {
    //     ShipOperation newShipOperation = shipOperationService.createShipOperation(shipOperation);
    //     return ResponseEntity.ok(newShipOperation);
    // }

    // @PutMapping("/ship-operations/{id}")
    // public ResponseEntity<ShipOperation> updateShipOperation(@PathVariable int id, @RequestBody ShipOperation shipOperation) {
    //     ShipOperation updatedShipOperation = shipOperationService.updateShipOperation(id, shipOperation);
    //     return ResponseEntity.ok(updatedShipOperation);
    // }

    // @DeleteMapping("/ship-operations/{id}")
    // public ResponseEntity<Void> deleteShipOperation(@PathVariable int id) {
    //     shipOperationService.deleteShipOperation(id);
    //     return ResponseEntity.noContent().build();
    // }
}
