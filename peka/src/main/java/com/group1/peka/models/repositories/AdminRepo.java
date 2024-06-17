package com.group1.peka.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.group1.peka.models.entities.Admin;

public interface AdminRepo extends CrudRepository<Admin, String> {

    Optional<Admin> findByEmail(String email);
    
}
