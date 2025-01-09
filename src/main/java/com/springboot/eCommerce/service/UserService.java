package com.springboot.eCommerce.service;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.entity.User;

import java.util.List;

public interface UserService {
    User createRequest(UserCreationRequest request);
    List<User> getUsers();
}
