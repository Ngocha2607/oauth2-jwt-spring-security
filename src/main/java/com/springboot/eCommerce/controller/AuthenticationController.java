package com.springboot.eCommerce.controller;

import com.nimbusds.jose.JOSEException;
import com.springboot.eCommerce.dto.request.*;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.AuthenticationResponse;
import com.springboot.eCommerce.dto.response.IntrospectResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.service.AuthenticationServiceInterface;
import com.springboot.eCommerce.service.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @PostMapping("/verify-token")
    IntrospectResponse verifyToken(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return authenticationServiceInterface.introspect(request);
    }

    @PostMapping("/logout")
    void logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationServiceInterface.logout(request);
    }

}
