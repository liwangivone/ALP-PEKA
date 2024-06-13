package com.group1.peka.dto.admin;

import java.util.ArrayList;
import java.util.List;

public class AdminListData {

    private List<AdminData> admin = new ArrayList<>();

    public AdminListData(List<AdminData> admin) {
        this.admin = admin;
    }

    public List<AdminData> getAdmin() {
        return admin;
    }

    public void setAdmin(List<AdminData> admin) {
        this.admin = admin;
    }   
}
