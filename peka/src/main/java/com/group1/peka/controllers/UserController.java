package com.group1.peka.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.dto.user.UserData;
import com.group1.peka.dto.user.UserListData;
import com.group1.peka.models.entities.User;
import com.group1.peka.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/email")
    public ResponseEntity<ResponseData<UserListData>> getUserByEmail(
        @RequestParam String email) {
        ResponseData<UserListData> responseData = new ResponseData<>();
        List<UserData> result = new ArrayList<>();

        Optional<User> user = userService.getUserByEmail(email);

        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        result.add(new UserData(
                user.get().getEmail(),
                user.get().getName()));
              
           
        responseData.setStatus(true);
        responseData.setPayload(new UserListData(result));
        
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<UserListData>> getallUser() {
        ResponseData<UserListData> responseData = new ResponseData<>();
        Iterable<User> allUser = userService.getAllUser();
        List<UserData> result = new ArrayList<>();

        for (User user : allUser) {
            result.add(new UserData(
                user.getEmail(),
                user.getName()));
        }

        responseData.setStatus(true);
        responseData.setPayload(new UserListData(result));
        
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/email")
    public ResponseEntity<ResponseData<UserListData>> updateUser(
            @RequestParam String name,
            @RequestParam String email) {
        ResponseData<UserListData> responseData = new ResponseData<>();
        List<UserData> result = new ArrayList<>();
        Optional<User> user = userService.getUserByEmail(email);

        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        User userNew = new User(
            email,
            name,
            user.get().getPassword());
                

        result.add(new UserData(
                email,
                name));

        userService.updateUser(userNew);
        responseData.setStatus(true);
        responseData.setPayload(new UserListData(result));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/email")
    public ResponseEntity<ResponseData<User>> delete(
        @RequestParam String email) {
        ResponseData<User> responseData = new ResponseData<>();

        Optional<User> user = userService.getUserByEmail(email);

        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        userService.deleteUserByEmail(email);
        responseData.setStatus(true);
        responseData.getMessages().add("The user " + user.get().getName() + " is deleted");

        return ResponseEntity.ok(responseData);
    }  
}
