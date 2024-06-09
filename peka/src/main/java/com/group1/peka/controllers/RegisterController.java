package com.group1.peka.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.dto.user.UserData;
import com.group1.peka.dto.user.UserListData;
import com.group1.peka.models.entities.User;
import com.group1.peka.models.entities.User.Gender;
import com.group1.peka.services.UserService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/user/{id}")
    public ResponseEntity<ResponseData<UserListData>> createUser(
            @PathVariable("id") String userID,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Gender gender,
            @RequestParam String phoneNumber) {
        
        ResponseData<UserListData> responseData = new ResponseData<>();
        List<UserData> result = new ArrayList<>();

        User userCheck = userService.createUser(
            userID,
            name, 
            gender, 
            email, 
            phoneNumber, 
            password);

        if (userCheck == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Email '" + email + "' already used");
            return ResponseEntity.badRequest().body(responseData);
        }

        result.add(new UserData(
            userCheck.getUserID(),
            userCheck.getName(),
            userCheck.getGender(), 
            userCheck.getEmail(),
            userCheck.getPhoneNumber()));

        responseData.setStatus(true);
        responseData.setPayload(new UserListData(result));
        return ResponseEntity.ok(responseData);
    }  
}
