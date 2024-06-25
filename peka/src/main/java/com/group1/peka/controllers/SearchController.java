package com.group1.peka.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.models.entities.ShipSchedule;
import com.group1.peka.services.ShipScheduleService;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private ShipScheduleService shipScheduleService;

    @PostMapping("/filter")
    public ResponseEntity<ResponseData<List<ShipSchedule>>> searchSchedule(
            @RequestParam String originName,
            @RequestParam String destinationName,
            @RequestParam String departureTime,
            @RequestParam String arrivalTime) {
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime departureDateTime = LocalDateTime.parse(departureTime, formatter);
                LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalTime, formatter);
        
        ResponseData<List<ShipSchedule>> responseData = new ResponseData<>();

        List<ShipSchedule> shipSchedules = shipScheduleService.filterSchedules(
                originName, destinationName, departureDateTime, arrivalDateTime);

        if (shipSchedules.isEmpty()) {
            responseData.setStatus(false);
            responseData.getMessages().add("No matching ship schedules found");
            return ResponseEntity.badRequest().body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(shipSchedules);
        return ResponseEntity.ok(responseData);
    }
}
