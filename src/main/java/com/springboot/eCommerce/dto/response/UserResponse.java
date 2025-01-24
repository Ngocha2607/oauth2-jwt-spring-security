package com.springboot.eCommerce.dto.response;

import com.springboot.eCommerce.entity.Role;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Set<RoleResponse> roles;
}
