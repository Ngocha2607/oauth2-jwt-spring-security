package com.springboot.eCommerce.controller;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.request.UserUpdationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.entity.User;
import com.springboot.eCommerce.service.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @PostMapping("/store")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {

        return userServiceInterface.createRequest(request);
    }

    @GetMapping("/list-all")
    List<UserResponse> getUsers() {
        return userServiceInterface.getUsers();
    }

    @GetMapping("/detail/{userId}")
    UserResponse getDetail(@PathVariable() String userId) {
        return userServiceInterface.getUser(userId);
    }

    @PutMapping("/update/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdationRequest request) {
        return userServiceInterface.updateUser(userId, request);
    }

    @DeleteMapping("/delete/{userId}")
    String deleteUser(@PathVariable String userId) {
        userServiceInterface.deleteUser(userId);
        return "Delete Successfully!";
    }

}
