package com.springboot.eCommerce.service;

import com.springboot.eCommerce.dto.request.PermissionCreationRequest;
import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.request.UserUpdationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.PermissionResponse;
import com.springboot.eCommerce.dto.response.UserResponse;

import java.util.List;

public interface PermissionServiceInterface {
    ApiResponse<PermissionResponse> create(PermissionCreationRequest request);
    ApiResponse<List<PermissionResponse>> getAll();
    void deletePermission(String name);
}
