package com.group1.peka.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.dto.user.UserData;
import com.group1.peka.dto.user.UserListData;
import com.group1.peka.models.entities.User;
import com.group1.peka.models.entities.User.Gender;
import com.group1.peka.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<UserListData>> getUserByID(@PathVariable("id") String id) {
        ResponseData<UserListData> responseData = new ResponseData<>();
        List<UserData> result = new ArrayList<>();

        Optional<User> user = userService.getUserByID(id);

        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        result.add(new UserData(
            user.get().getUserID(),
            user.get().getName(),
            user.get().getGender(),
            user.get().getEmail(),
            user.get().getPhoneNumber()));
        
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
                user.getUserID(),
                user.getName(),
                user.getGender(),
                user.getEmail(),
                user.getPhoneNumber()));
        }

        responseData.setStatus(true);
        responseData.setPayload(new UserListData(result));
        
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<UserListData>> updateUser(
            @PathVariable("id") String userID,
            @RequestParam String name,
            @RequestParam Gender gender,
            @RequestParam String email,
            @RequestParam String phoneNumber) {
        ResponseData<UserListData> responseData = new ResponseData<>();
        List<UserData> result = new ArrayList<>();
        Optional<User> user = userService.getUserByID(userID);

        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        User userNew = new User(
                userID,
                name,
                gender,
                email,
                phoneNumber,
                user.get().getPassword());

        result.add(new UserData(
                userID,
                name,
                gender,
                email,
                phoneNumber));

        userService.updateUser(userNew);
        responseData.setStatus(true);
        responseData.setPayload(new UserListData(result));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<User>> delete(@PathVariable("id") String id) {
        ResponseData<User> responseData = new ResponseData<>();

        Optional<User> user = userService.getUserByID(id);

        if (!user.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("User not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        userService.deleteUserByID(id);
        responseData.setStatus(true);
        responseData.getMessages().add("User is deleted");

        return ResponseEntity.ok(responseData);
    }  
}
