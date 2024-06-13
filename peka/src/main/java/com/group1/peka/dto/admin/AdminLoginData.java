package com.group1.peka.dto.admin;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminLoginData {

    private boolean login;

    private List<AdminData> admin = new ArrayList<>();


    public AdminLoginData(boolean login, List<AdminData> admin) {
        this.login = login;
        this.admin = admin;
    } 
}
