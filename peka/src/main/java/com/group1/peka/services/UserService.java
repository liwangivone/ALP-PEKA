package com.group1.peka.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.User;
import com.group1.peka.models.repositories.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(String email, String name, String password) {
        Optional<User> emailCheck = userRepo.findByEmail(email);

        if (emailCheck.isPresent()) {
            return null;
        }

        User user = new User(email, name, password);

        return userRepo.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Iterable<User> getAllUser() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUserByEmail(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        user.ifPresent(userRepo::delete);
    }
}
