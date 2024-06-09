package com.group1.peka.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group1.peka.dto.ResponseData;
import com.group1.peka.dto.user.UserData;
import com.group1.peka.dto.user.UserLoginData;
import com.group1.peka.models.entities.User;
import com.group1.peka.services.UserService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ResponseData<UserLoginData>> loginUser(
            @RequestParam String email,
            @RequestParam String password) {
        
        ResponseData<UserLoginData> responseData =  new ResponseData<>();

        Optional<User> user = userService.getUserByEmail(email);

        if (user.isPresent()) {
            if (password.equals(user.get().getPassword())) {

                UserData userData = new UserData(
                    user.get().getUserID(),
                    user.get().getName(),
                    user.get().getGender(),
                    user.get().getEmail(),
                    user.get().getPhoneNumber()); 

                List<UserData> userList = new ArrayList<>();
                userList.add(userData);

                responseData.setStatus(true);
                responseData.getMessages().add("Successfully logged in");
                responseData.setPayload(new UserLoginData(true, userList));

                return ResponseEntity.ok(responseData);
            }

            else {
                List<UserData> userList = new ArrayList<>();

                responseData.getMessages().add("Incorrect password");
                responseData.setPayload(new UserLoginData(false, userList));

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
            }
        }
        List<UserData> userList = new ArrayList<>();

        responseData.setStatus(false);
        responseData.getMessages().add("Invalid email");
        responseData.setPayload(new UserLoginData(false, userList));

        return ResponseEntity.badRequest().body(responseData);
        }    
}
