package com.group1.peka.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private String userID;

    @NotEmpty(message = "Full name is required")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotEmpty(message = "Username is required")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotEmpty(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    

    public enum Gender {
        FEMALE,
        MALE
    }

    public User(String userID, String fullName, String gender, String username, String password, String phoneNumber) {
        this.userID = userID;
        this.fullName = fullName;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
