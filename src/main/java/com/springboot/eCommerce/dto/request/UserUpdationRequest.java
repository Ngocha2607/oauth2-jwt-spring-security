package com.springboot.eCommerce.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserUpdationRequest {
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Set<String> roles;
}
