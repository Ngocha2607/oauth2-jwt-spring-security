package com.springboot.eCommerce.dto.request;

import com.springboot.eCommerce.entity.Role;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class PermissionCreationRequest {

    @Size(min = 3, message = "PERMISSION_NAME_INVALID")
    private String name;
    private String description;

}
