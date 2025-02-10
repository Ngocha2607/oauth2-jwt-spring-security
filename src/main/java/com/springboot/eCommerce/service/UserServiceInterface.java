package com.springboot.eCommerce.service;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.request.UserUpdationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.UserPaginationResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserServiceInterface {
    ApiResponse<UserResponse> createRequest(UserCreationRequest request);
    List<UserResponse> getUsers();
    UserPaginationResponse getPaginationUsers(Integer pageSize, Integer pageIndex, String sort);
    UserResponse getUser(String userId);
    UserResponse getMyInfo();
    UserResponse updateUser(String userId, UserUpdationRequest request);
    void deleteUser(String userId);
}
