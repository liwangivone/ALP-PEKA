package com.group1.peka.dto.user;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListData {

    private List<?> user = new ArrayList<>();

    public UserListData(List<?> user) {
        this.user = user;
    }  
}
