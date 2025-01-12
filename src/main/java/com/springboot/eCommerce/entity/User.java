package com.springboot.eCommerce.entity;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public User() {

    };

    public User(UserCreationRequest request) {
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.dob = request.getDob();
    }
}
