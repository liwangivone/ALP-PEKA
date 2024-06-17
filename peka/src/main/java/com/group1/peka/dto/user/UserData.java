package com.group1.peka.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserData {

    private String email;

    private String name;


    public UserData(String email, String name) {
        this.email = email;
        this.name = name;
    }   
}
