package com.group1.peka.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.dto.ship.ShipData;
import com.group1.peka.dto.ship.ShipListData;
import com.group1.peka.models.entities.Ship;
import com.group1.peka.services.ShipService;

@RestController
@RequestMapping("/api/ship")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @PostMapping("/admin/add")
    public ResponseEntity<ResponseData<ShipListData>> createShip(
            @RequestParam String shipName,
            @RequestParam int capacity,
            @RequestParam String status) {
        
        ResponseData<ShipListData> responseData = new ResponseData<>();
        List<ShipData> result = new ArrayList<>();

        Ship shipCheck = shipService.createShip(shipName, capacity, status);

        if (shipCheck == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("The ship " + shipName + " already exists");

            return ResponseEntity.badRequest().body(responseData);
        }

        result.add(new ShipData(
            shipCheck.getShipID(),
            shipCheck.getShipName(),
            capacity,
            status));

        responseData.setStatus(true);
        responseData.setPayload(new ShipListData(result));

        return ResponseEntity.ok(responseData);
        }

    @GetMapping("/id")
    public ResponseEntity<ResponseData<ShipListData>> getShipByID(
        @RequestParam int shipID) {
        ResponseData<ShipListData> responseData = new ResponseData<>();
        List<ShipData> result = new ArrayList<>();

        Optional<Ship> ship = shipService.getShipByID(shipID);

        if (!ship.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Ship not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        result.add(new ShipData(
            ship.get().getShipID(),
            ship.get().getShipName(),
            ship.get().getCapacity(),
            ship.get().getStatus()));

        responseData.setStatus(true);
        responseData.setPayload(new ShipListData(result));

        return ResponseEntity.ok(responseData);
        }

    @PutMapping("/id")
    public ResponseEntity<ResponseData<ShipListData>> updateShip (
            @RequestParam int shipID,
            @RequestParam String shipName,
            @RequestParam int capacity,
            @RequestParam String status) {
        
        ResponseData<ShipListData> responseData = new ResponseData<>();
        List<ShipData> result = new ArrayList<>();
        Optional<Ship> ship = shipService.getShipByID(shipID);

        if (!ship.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Ship not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        Ship shipNew = new Ship(
                shipID, 
                shipName,
                capacity,
                status);

        result.add(new ShipData(
                shipID,
                shipName,
                capacity,
                status));

        shipService.updateShip(shipNew);
        responseData.setStatus(true);
        responseData.setPayload(new ShipListData(result));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/id/name")
    public ResponseEntity<ResponseData<Ship>> delete(
        @RequestParam int shipID,
        @RequestParam String shipName) {

        ResponseData<Ship> responseData = new ResponseData<>();

        boolean isDeleted = shipService.deleteShipByIdAndName(shipID, shipName);

        if (!isDeleted) {
            responseData.setStatus(false);
            responseData.getMessages().add("Ship not found or name does not match");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessages().add("The ship with ID " + shipID + " and name " + shipName + " is successfully deleted");

        return ResponseEntity.ok(responseData);
    }

}