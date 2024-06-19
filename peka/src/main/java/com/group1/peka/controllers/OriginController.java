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
import com.group1.peka.dto.origin.OriginData;
import com.group1.peka.dto.origin.OriginListData;
import com.group1.peka.models.entities.Origin;
import com.group1.peka.services.OriginService;

@RestController
@RequestMapping("api/origin")
public class OriginController {

    @Autowired
    private OriginService originService;

    @PostMapping("/admin/add")
    public ResponseEntity<ResponseData<OriginListData>> createOrigin(
            @RequestParam String originName) {
        
        ResponseData<OriginListData> responseData = new ResponseData<>();
        List<OriginData> result = new ArrayList<>();

        Origin originCheck = originService.createOrigin(originName);

        if (originCheck == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("The origin " + originName + " already exists");

            return ResponseEntity.badRequest().body(responseData);
        }

        result.add(new OriginData(
            originCheck.getOriginID(),
            originCheck.getOriginName()));

        responseData.setStatus(true);
        responseData.setPayload(new OriginListData(result));

        return ResponseEntity.ok(responseData);
        }

    @GetMapping("/id")
    public ResponseEntity<ResponseData<OriginListData>> getOriginByID(
        @RequestParam int originID) {
        ResponseData<OriginListData> responseData = new ResponseData<>();
        List<OriginData> result = new ArrayList<>();

        Optional<Origin> origin = originService.getOriginByID(originID);

        if (!origin.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Origin not found");
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    
        result.add(new OriginData(
            origin.get().getOriginID(),
            origin.get().getOriginName()));

        responseData.setStatus(true);
        responseData.setPayload(new OriginListData(result));

        return ResponseEntity.ok(responseData);
        }

    @GetMapping("/all")
        public ResponseEntity<ResponseData<OriginListData>> getAllOrigins() {
            ResponseData<OriginListData> responseData = new ResponseData<>();
            List<OriginData> result = new ArrayList<>();
        
            Iterable<Origin> origins = originService.getAllOrigins();
        
            for (Origin origin : origins) {
                result.add(new OriginData(
                        origin.getOriginID(),
                        origin.getOriginName()));
            }

            responseData.setStatus(true);
            responseData.setPayload(new OriginListData(result));
        
            return ResponseEntity.ok(responseData);
    }  

    @PutMapping("/id")
    public ResponseEntity<ResponseData<OriginListData>> updateOrigin (
            @RequestParam int originID,
            @RequestParam String originName) {
        
        ResponseData<OriginListData> responseData = new ResponseData<>();
        List<OriginData> result = new ArrayList<>();
        Optional<Origin> origin = originService.getOriginByID(originID);

        if (!origin.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Origin not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        Origin originNew = new Origin(
                originID, 
                originName);

        result.add(new OriginData(
                originID,
                originName));

        originService.updateOrigin(originNew);
        responseData.setStatus(true);
        responseData.setPayload(new OriginListData(result));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/id/name")
    public ResponseEntity<ResponseData<Origin>> delete(
        @RequestParam int originID,
        @RequestParam String originName) {

        ResponseData<Origin> responseData = new ResponseData<>();

        boolean isDeleted = originService.deleteOriginByIdAndName(originID, originName);

        if (!isDeleted) {
            responseData.setStatus(false);
            responseData.getMessages().add("Origin not found or name does not match");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessages().add("The origin with ID " + originID + " and name " + originName + " is successfully deleted");

        return ResponseEntity.ok(responseData);
    }
}
