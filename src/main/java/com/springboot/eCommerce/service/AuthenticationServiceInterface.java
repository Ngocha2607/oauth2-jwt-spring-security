package com.springboot.eCommerce.service;

import com.nimbusds.jose.JOSEException;
import com.springboot.eCommerce.dto.request.AuthenticationRequest;
import com.springboot.eCommerce.dto.request.IntrospectRequest;
import com.springboot.eCommerce.dto.request.LogoutRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.AuthenticationResponse;
import com.springboot.eCommerce.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationServiceInterface {
    ApiResponse<AuthenticationResponse> login(AuthenticationRequest request);
    void logout(LogoutRequest request) throws JOSEException, ParseException;
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
