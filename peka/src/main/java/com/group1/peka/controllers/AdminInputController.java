// package com.group1.peka.controllers;

// import java.time.LocalDateTime;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.group1.peka.dto.ResponseData;
// import com.group1.peka.models.entities.Destination;
// import com.group1.peka.models.entities.Origin;
// import com.group1.peka.models.entities.Ship;
// import com.group1.peka.models.entities.ShipSchedule;
// import com.group1.peka.services.DestinationService;
// import com.group1.peka.services.OriginService;
// import com.group1.peka.services.ShipScheduleService;
// import com.group1.peka.services.ShipService;

// @RestController
// @RequestMapping("/api/admin")
// public class AdminInputController {

//     @Autowired
//     private ShipService shipService;

//     @Autowired
//     private OriginService originService;

//     @Autowired
//     private DestinationService destinationService;

//     @Autowired
//     private ShipScheduleService shipScheduleService;

//     // Origin Endpoints
//     @PostMapping("/origin")
//     public ResponseEntity<ResponseData<Origin>> createOrigin(
//         @RequestParam String originName) {
//         ResponseData<Origin> responseData = new ResponseData<>();
//         try {
//             Origin origin = new Origin(originName);
//             responseData.setStatus(true);
//             responseData.setPayload(originService.createOrigin(origin));
//             return ResponseEntity.ok(responseData);
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.badRequest().body(responseData);
//         }
//     }

//     @PutMapping("/origins/{id}")
//     public ResponseEntity<ResponseData<Origin>> updateOrigin(@PathVariable int id, @RequestParam String name) {
//         ResponseData<Origin> responseData = new ResponseData<>();
//         try {
//             Origin originDetails = new Origin();
//             originDetails.setName(name);
//             responseData.setStatus(true);
//             responseData.setPayload(originService.updateOrigin(id, originDetails));
//             return ResponseEntity.ok(responseData);
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.badRequest().body(responseData);
//         }
//     }

//     @DeleteMapping("/origins/{id}")
//     public ResponseEntity<ResponseData<Void>> deleteOrigin(@PathVariable int id) {
//         ResponseData<Void> responseData = new ResponseData<>();
//         try {
//             originService.deleteOrigin(id);
//             responseData.setStatus(true);
//             return ResponseEntity.noContent().build();
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
//         }
//     }

//     // Destination Endpoints
//     @PostMapping("/destinations")
//     public ResponseEntity<ResponseData<Destination>> createDestination(@RequestParam String name) {
//         ResponseData<Destination> responseData = new ResponseData<>();
//         try {
//             Destination destination = new Destination();
//             destination.setName(name);
//             responseData.setStatus(true);
//             responseData.setPayload(destinationService.createDestination(destination));
//             return ResponseEntity.ok(responseData);
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.badRequest().body(responseData);
//         }
//     }

//     @PutMapping("/destinations/{id}")
//     public ResponseEntity<ResponseData<Destination>> updateDestination(@PathVariable int id, @RequestParam String name) {
//         ResponseData<Destination> responseData = new ResponseData<>();
//         try {
//             Destination destinationDetails = new Destination();
//             destinationDetails.setName(name);
//             responseData.setStatus(true);
//             responseData.setPayload(destinationService.updateDestination(id, destinationDetails));
//             return ResponseEntity.ok(responseData);
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.badRequest().body(responseData);
//         }
//     }

//     @DeleteMapping("/destinations/{id}")
//     public ResponseEntity<ResponseData<Void>> deleteDestination(@PathVariable int id) {
//         ResponseData<Void> responseData = new ResponseData<>();
//         try {
//             destinationService.deleteDestination(id);
//             responseData.setStatus(true);
//             return ResponseEntity.noContent().build();
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
//         }
//     }

//     // Ship Endpoints
//     @PostMapping("/ships")
//     public ResponseEntity<ResponseData<Ship>> createShip(@RequestParam String name, @RequestParam int capacity) {
//         ResponseData<Ship> responseData = new ResponseData<>();
//         try {
//             Ship ship = new Ship();
//             ship.setName(name);
//             ship.setCapacity(capacity);
//             responseData.setStatus(true);
//             responseData.setPayload(shipService.createShip(ship));
//             return ResponseEntity.ok(responseData);
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.badRequest().body(responseData);
//         }
//     }

//     @PutMapping("/ships/{id}")
//     public ResponseEntity<ResponseData<Ship>> updateShip(@PathVariable int id, @RequestParam String name, @RequestParam int capacity) {
//         ResponseData<Ship> responseData = new ResponseData<>();
//         try {
//             Ship shipDetails = new Ship();
//             shipDetails.setName(name);
//             shipDetails.setCapacity(capacity);
//             responseData.setStatus(true);
//             responseData.setPayload(shipService.updateShip(id, shipDetails));
//             return ResponseEntity.ok(responseData);
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.badRequest().body(responseData);
//         }
//     }

//     @DeleteMapping("/ships/{id}")
//     public ResponseEntity<ResponseData<Void>> deleteShip(@PathVariable int id) {
//         ResponseData<Void> responseData = new ResponseData<>();
//         try {
//             shipService.deleteShip(id);
//             responseData.setStatus(true);
//             return ResponseEntity.noContent().build();
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
//         }
//     }

//     // ShipSchedule Endpoints
//     @PostMapping("/shipSchedules")
//     public ResponseEntity<ResponseData<ShipSchedule>> createShipSchedule(
//             @RequestParam int shipID,
//             @RequestParam int originID,
//             @RequestParam int destinationID,
//             @RequestParam int adultPrice,
//             @RequestParam int childPrice,
//             @RequestParam LocalDateTime departureTime,
//             @RequestParam LocalDateTime arrivalTime) {
//         ResponseData<ShipSchedule> responseData = new ResponseData<>();
//         try {
//             ShipSchedule shipSchedule = shipScheduleService.createShipSchedule(shipID, originID, destinationID, adultPrice, childPrice, departureTime, arrivalTime);
//             responseData.setStatus(true);
//             responseData.setPayload(shipSchedule);
//             return ResponseEntity.ok(responseData);
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.badRequest().body(responseData);
//         }
//     }

//     @PutMapping("/shipSchedules/{id}")
//     public ResponseEntity<ResponseData<ShipSchedule>> updateShipSchedule(
//             @PathVariable int id,
//             @RequestParam int shipID,
//             @RequestParam int originID,
//             @RequestParam int destinationID,
//             @RequestParam int adultPrice,
//             @RequestParam int childPrice,
//             @RequestParam LocalDateTime departureTime,
//             @RequestParam LocalDateTime arrivalTime) {
//         ResponseData<ShipSchedule> responseData = new ResponseData<>();
//         try {
//             ShipSchedule shipSchedule = shipScheduleService.updateShipSchedule(id, shipID, originID, destinationID, adultPrice, childPrice, departureTime, arrivalTime);
//             responseData.setStatus(true);
//             responseData.setPayload(shipSchedule);
//             return ResponseEntity.ok(responseData);
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.badRequest().body(responseData);
//         }
//     }

//     @DeleteMapping("/shipSchedules/{id}")
//     public ResponseEntity<ResponseData<Void>> deleteShipSchedule(@PathVariable int id) {
//         ResponseData<Void> responseData = new ResponseData<>();
//         try {
//             shipScheduleService.deleteShipSchedule(id);
//             responseData.setStatus(true);
//             return ResponseEntity.noContent().build();
//         } catch (Exception e) {
//             responseData.setStatus(false);
//             responseData.getMessages().add(e.getMessage());
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
//         }
//     }
// }
