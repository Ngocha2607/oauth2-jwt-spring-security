package com.springboot.eCommerce.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdationRequest {
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
