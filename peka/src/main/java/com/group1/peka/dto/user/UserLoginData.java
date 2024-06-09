package com.group1.peka.dto.user;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginData {

    private boolean login;

    private List<UserData> user = new ArrayList<>();


    public UserLoginData(boolean login, List<UserData> user) {
        this.login = login;
        this.user = user;
    }
    
    
}


