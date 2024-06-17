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
import com.group1.peka.dto.destination.DestinationData;
import com.group1.peka.dto.destination.DestinationListData;
import com.group1.peka.models.entities.Destination;
import com.group1.peka.services.DestinationService;

@RestController
@RequestMapping("/api/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping("/admin/add")
    public ResponseEntity<ResponseData<DestinationListData>> createDestination(
            @RequestParam String destinationName) {
        
        ResponseData<DestinationListData> responseData = new ResponseData<>();
        List<DestinationData> result = new ArrayList<>();

        Destination destinationCheck = destinationService.createDestination(destinationName);

        if (destinationCheck == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("The destination " + destinationName + " already exists");

            return ResponseEntity.badRequest().body(responseData);
        }

        result.add(new DestinationData(
            '0',
            destinationCheck.getDestinationName()));

        responseData.setStatus(true);
        responseData.setPayload(new DestinationListData(result));

        return ResponseEntity.ok(responseData);
        }

    @GetMapping("/id")
    public ResponseEntity<ResponseData<DestinationListData>> getDestinationByID(
        @RequestParam int destinationID) {
        ResponseData<DestinationListData> responseData = new ResponseData<>();
        List<DestinationData> result = new ArrayList<>();

        Optional<Destination> destination = destinationService.getDestinationByID(destinationID);

        if (!destination.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Destination not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        result.add(new DestinationData(
            '0',
            destination.get().getDestinationName()));

        responseData.setStatus(true);
        responseData.setPayload(new DestinationListData(result));

        return ResponseEntity.ok(responseData);
        }

    @PutMapping("/id")
    public ResponseEntity<ResponseData<DestinationListData>> updateDestination (
            @RequestParam int destinationID,
            @RequestParam String destinationName) {
        
        ResponseData<DestinationListData> responseData = new ResponseData<>();
        List<DestinationData> result = new ArrayList<>();
        Optional<Destination> destination = destinationService.getDestinationByID(destinationID);

        if (!destination.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Destination not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        Destination destinationNew = new Destination(
                destinationID, 
                destinationName);

        result.add(new DestinationData(
                destinationID,
                destinationName));

        destinationService.updateDestination(destinationNew);
        responseData.setStatus(true);
        responseData.setPayload(new DestinationListData(result));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/name")
    public ResponseEntity<ResponseData<Destination>> delete (
        @RequestParam String destinationName) {
        ResponseData<Destination> responseData = new ResponseData<>();

        Optional<Destination> destination = destinationService.getDestinationByDestinationName(destinationName);

        if (!destination.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Destination not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        destinationService.deleteDestinationByName(destinationName);
        responseData.setStatus(true);
        responseData.getMessages().add("The destination " + destination.get().getDestinationName() + " is successfully deleted");

        return ResponseEntity.ok(responseData);
    }
}

       