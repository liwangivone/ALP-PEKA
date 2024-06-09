package com.group1.peka.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.models.entities.Ship;
import com.group1.peka.models.entities.Ship.ShipType;
import com.group1.peka.services.ShipService;

@RestController
@RequestMapping("/api/ship")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Ship>> createShip(
            @RequestParam String shipID,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam ShipType shipType) {
        ResponseData<Ship> responseData = new ResponseData<>();

        Ship ship = shipService.createShip(shipID, name, capacity, shipType);

        responseData.setStatus(true);
        responseData.setPayload(ship);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Ship>> getShipByID(
            @PathVariable String id) {
        ResponseData<Ship> responseData = new ResponseData<>();

        Optional<Ship> ship = shipService.getShipByID(id);
        if (!ship.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Ship not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(ship.get());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<Iterable<Ship>>> getAllShips() {
        ResponseData<Iterable<Ship>> responseData = new ResponseData<>();
        Iterable<Ship> ships = shipService.getAllShips();
        responseData.setStatus(true);
        responseData.setPayload(ships);
        return ResponseEntity.ok(responseData);
    }
}
