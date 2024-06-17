package com.group1.peka.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @NotEmpty(message = "Email is required")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
