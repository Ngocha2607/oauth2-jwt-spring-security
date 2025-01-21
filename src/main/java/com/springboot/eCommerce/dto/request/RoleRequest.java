package com.springboot.eCommerce.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RoleRequest {

    @Size(min = 3, message = "PERMISSION_NAME_INVALID")
    private String name;
    private String description;
    Set<String> permissions;

}
