// package com.group1.peka.controllers;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.group1.peka.dto.ResponseData;
// import com.group1.peka.models.entities.ShipSchedule;
// import com.group1.peka.models.entities.Ticket;
// import com.group1.peka.models.entities.Transaction;
// import com.group1.peka.services.TicketService;
// import com.group1.peka.services.TransactionService;

// @RestController
// @RequestMapping("/api/ticket")
// public class TicketController {

//     @Autowired
//     private TicketService ticketService;

//     @Autowired
//     private ShipSchedule shipScheduleService;

//     @Autowired
//     private TransactionService transactionService;
    
//     @PostMapping("/ticket/{id}")
//     public ResponseEntity<ResponseData<Ticket>> createTicket(
//             @PathVariable("id") int ticketID,
//             @RequestParam int shipOperationID,
//             @RequestParam int transactionID,
//             @RequestParam int price,
//             @RequestParam String passengerName,
//             @RequestParam String passengerType) {

//         ResponseData<Ticket> responseData = new ResponseData<>();
//         List<Ticket> result = new ArrayList<>();

//         // // Ensure shipOperation exists
//         // ShipSchedule shipOperation = shipOperationService.getShipOperationbyID(shipOperationID).orElse(null);
//         // if (shipOperation == null) {
//         //     responseData.setStatus(false);
//         //     responseData.getMessages().add("Ship operation not found");
//         //     return ResponseEntity.badRequest().body(responseData);
//         // }

//         // Ensure transaction exists
//         Transaction transaction = transactionService.getTransactionByID(transactionID).orElse(null);
//         if (transaction == null) {
//             responseData.setStatus(false);
//             responseData.getMessages().add("Transaction not found");
//             return ResponseEntity.badRequest().body(responseData);
//         }

//         // // Save the ticket
//         // Ticket ticketCheck = ticketService.saveTicket(
//         //     ticketID,
//         //     shipsch, 
//         //     transaction, 
//         //     price,
//         //     passengerName,
//         //     passengerType);

//         // Add the created ticket to the result list
//         result.add(new TicketData(
//             ticketCheck.getTicketID(),
//             ticketCheck.getPrice(),
//             ticketCheck.getPassengerName(),
//             ticketCheck.getPassengerType()));

//         // Prepare response data
//         responseData.setStatus(true);
//         responseData.setPayload(new TicketListData(result));
//         return ResponseEntity.ok(responseData);
//     }
// }
