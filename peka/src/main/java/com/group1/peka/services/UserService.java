package com.group1.peka.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.User;
import com.group1.peka.models.entities.User.Gender;
import com.group1.peka.models.repositories.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(String userID, String name, Gender gender, String email, String password, String phoneNumber) {
        Optional<User> emailCheck = userRepo.findByEmail(email);

        if (emailCheck.isPresent()) {
            return null;
        }

        User user = new User(userID, name, gender, email, password, phoneNumber);

        return userRepo.save(user);

    }

    public Optional<User> getUserByID(String id) {
        return userRepo.findById(id);
    }

    public Iterable<User> getAllUser() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUserByID(String id) {
        userRepo.deleteById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
