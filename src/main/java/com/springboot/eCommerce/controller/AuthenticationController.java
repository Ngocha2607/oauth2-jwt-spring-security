package com.springboot.eCommerce.controller;

import com.springboot.eCommerce.dto.request.AuthenticationRequest;
import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.request.UserUpdationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.AuthenticationResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.service.AuthenticationServiceInterface;
import com.springboot.eCommerce.service.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceInterface authenticationServiceInterface;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        return authenticationServiceInterface.login(request);
    }

}
