package com.springboot.eCommerce.service;

import com.springboot.eCommerce.dto.request.AuthenticationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.AuthenticationResponse;

public interface AuthenticationServiceInterface {
    ApiResponse<AuthenticationResponse> login(AuthenticationRequest request);
}
