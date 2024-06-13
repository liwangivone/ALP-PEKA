package com.group1.peka.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.Admin;
import com.group1.peka.models.repositories.AdminRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {
    
    @Autowired
    private AdminRepo adminRepo;

    public Admin createAdmin(String email, String name, String password) {
        Optional<Admin> emailcheck = adminRepo.findByEmail(email);

        if (emailcheck.isPresent()) {
            return null;
        }

        Admin admin = new Admin(email, name, password);

        return adminRepo.save(admin);
    }

    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepo.findByEmail(email);
    }

    public Iterable<Admin> getAllAdmin() {
        return adminRepo.findAll(); 
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    public void deleteAdminByEmail(String email) {
        Optional<Admin> admin = adminRepo.findByEmail(email);
        admin.ifPresent(adminRepo::delete);
    }
}
