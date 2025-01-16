package com.springboot.eCommerce.service;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.request.UserUpdationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.entity.User;

import java.util.List;

public interface UserServiceInterface {
    ApiResponse<UserResponse> createRequest(UserCreationRequest request);
    List<UserResponse> getUsers();
    UserResponse getUser(String userId);
    UserResponse getMyInfo();
    UserResponse updateUser(String userId, UserUpdationRequest request);
    void deleteUser(String userId);
}
