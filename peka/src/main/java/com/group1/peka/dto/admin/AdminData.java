package com.group1.peka.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminData {

    private String email;

    private String name;

    
    public AdminData(String email, String name) {
        this.email = email;
        this.name = name;
    } 
}
