package com.group1.peka.dto.user;

import com.group1.peka.models.entities.User.Gender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserData {

    private String userID;

    private String name;

    private Gender gender;

    private String phoneNumber;

    private String email;


    public UserData(String userID, String name, Gender gender, String email, String phoneNumber) {
        this.userID = userID;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }   
}
