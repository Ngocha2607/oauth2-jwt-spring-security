package com.springboot.eCommerce.service;

import com.springboot.eCommerce.dto.request.RoleRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.RoleResponse;

import java.util.List;

public interface RoleServiceInterface {
    ApiResponse<RoleResponse> create(RoleRequest request);
    ApiResponse<List<RoleResponse>> getAll();
    void deleteRole(String name);
}
