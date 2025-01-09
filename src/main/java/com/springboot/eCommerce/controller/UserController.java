package com.springboot.eCommerce.controller;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.entity.User;
import com.springboot.eCommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/store")
    User createUser(@RequestBody UserCreationRequest request) {
        return userService.createRequest(request);
    }

    @GetMapping("/list-all")
    List<User> getUsers() {
        return userService.getUsers();
    }
}
