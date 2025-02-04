package com.springboot.eCommerce.controller;

import com.springboot.eCommerce.dto.request.PermissionCreationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.PermissionResponse;
import com.springboot.eCommerce.service.PermissionServiceInterface;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionServiceInterface permissionServiceInterface;

    @PostMapping("/store")
    public ApiResponse<PermissionResponse> create(@RequestBody @Valid PermissionCreationRequest permissionCreationRequest) {
        return permissionServiceInterface.create(permissionCreationRequest);
    }

    @GetMapping("/list")
    public ApiResponse<List<PermissionResponse>> getAll() {
        return permissionServiceInterface.getAll();
    }

    @DeleteMapping("/delete/{name}")
    public String delete(@PathVariable String name) {
        permissionServiceInterface.deletePermission(name);
        return "Delete permission successfully!";
    }
}
