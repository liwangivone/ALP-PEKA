package com.group1.peka.models.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.ShipSchedule;


public interface ShipScheduleRepo extends CrudRepository<ShipSchedule, Integer> {

    Optional<ShipSchedule> findByShipScheduleID(int shipScheduleID);

    boolean existsByShipScheduleID(int shipScheduleID);

    void deleteByShipScheduleID(int shipScheduleID);

    List<ShipSchedule> findByOriginOriginNameAndDestinationDestinationNameAndDepartureTimeAndArrivalTimeAndShipStatus(
        String originName, 
        String destinationName, 
        LocalDateTime departureTime, 
        LocalDateTime arrivalTime,
        String status);
  
}
