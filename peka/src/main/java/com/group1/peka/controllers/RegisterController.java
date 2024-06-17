package com.group1.peka.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.dto.user.UserData;
import com.group1.peka.dto.user.UserListData;
import com.group1.peka.models.entities.User;
import com.group1.peka.services.UserService;
import com.group1.peka.services.AdminService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @PostMapping("/user")
    public ResponseEntity<ResponseData<UserListData>> createUser(
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String password) {
        
        ResponseData<UserListData> responseData = new ResponseData<>();
        List<UserData> result = new ArrayList<>();

        User userCheck = userService.createUser(
            email, 
            name, 
            password);

        if (userCheck == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Email '" + email + "' already used");
            return ResponseEntity.badRequest().body(responseData);
        }

        result.add(new UserData(
            userCheck.getEmail(),
            userCheck.getName()));

        responseData.setStatus(true);
        responseData.setPayload(new UserListData(result));
        return ResponseEntity.ok(responseData);
    }  
}
