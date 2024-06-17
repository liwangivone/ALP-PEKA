package com.group1.peka.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.group1.peka.models.entities.User;


public interface UserRepo extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);   
}
