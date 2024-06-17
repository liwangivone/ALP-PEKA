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
import com.group1.peka.dto.admin.AdminData;
import com.group1.peka.dto.admin.AdminListData;
import com.group1.peka.models.entities.Admin;
import com.group1.peka.services.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/email")
    public ResponseEntity<ResponseData<AdminListData>> getAdminByEmail(
        @RequestParam String email) {
        ResponseData<AdminListData> responseData = new ResponseData<>();
        List<AdminData> result = new ArrayList<>();

        Optional<Admin> admin = adminService.getAdminByEmail(email);

        if (!admin.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Admin not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        result.add(new AdminData(
                admin.get().getEmail(),
                admin.get().getName()));
              
           
        responseData.setStatus(true);
        responseData.setPayload(new AdminListData(result));
        
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData<AdminListData>> getallAdmin() {
        ResponseData<AdminListData> responseData = new ResponseData<>();
        Iterable<Admin> allAdmin = adminService.getAllAdmin();
        List<AdminData> result = new ArrayList<>();

        for (Admin admin : allAdmin) {
            result.add(new AdminData(
                admin.getEmail(),
                admin.getName()));
        }

        responseData.setStatus(true);
        responseData.setPayload(new AdminListData(result));
        
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/email")
    public ResponseEntity<ResponseData<AdminListData>> updateAdmin(
            @RequestParam String name,
            @RequestParam String email) {
        ResponseData<AdminListData> responseData = new ResponseData<>();
        List<AdminData> result = new ArrayList<>();
        Optional<Admin> admin = adminService.getAdminByEmail(email);

        if (!admin.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Admin not found");

            return ResponseEntity.badRequest().body(responseData);
        }

        Admin adminNew = new Admin(
            email,
            name,
            admin.get().getPassword());
                

        result.add(new AdminData(
                email,
                name));

        adminService.updateAdmin(adminNew);
        responseData.setStatus(true);
        responseData.setPayload(new AdminListData(result));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/email")
    public ResponseEntity<ResponseData<Admin>> delete(
        @RequestParam String email) {
        ResponseData<Admin> responseData = new ResponseData<>();

        Optional<Admin> admin = adminService.getAdminByEmail(email);

        if (!admin.isPresent()) {
            responseData.setStatus(false);
            responseData.getMessages().add("Email not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        adminService.deleteAdminByEmail(email);
        responseData.setStatus(true);
        responseData.getMessages().add("The admin " + admin.get().getName() + " is deleted");

        return ResponseEntity.ok(responseData);
    }
}
