package com.springboot.eCommerce.controller;

import com.springboot.eCommerce.dto.request.PermissionCreationRequest;
import com.springboot.eCommerce.dto.request.RoleRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.PermissionResponse;
import com.springboot.eCommerce.dto.response.RoleResponse;
import com.springboot.eCommerce.service.PermissionServiceInterface;
import com.springboot.eCommerce.service.RoleServiceInterface;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@SecurityRequirement(name = "eCommerce Application")
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleServiceInterface roleServiceInterface;

    @PostMapping("/store")
    public ApiResponse<RoleResponse> create(@RequestBody @Valid RoleRequest request) {
        return roleServiceInterface.create(request);
    }

    @GetMapping("/list")
    public ApiResponse<List<RoleResponse>> getAll() {
        return roleServiceInterface.getAll();
    }

    @DeleteMapping("/delete/{name}")
    public String delete(@PathVariable String name) {
        roleServiceInterface.deleteRole(name);
        return "Delete role successfully!";
    }
}
