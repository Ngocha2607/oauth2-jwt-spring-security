package com.springboot.eCommerce.service;

import com.springboot.eCommerce.dto.request.PermissionCreationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.PermissionResponse;

import java.util.List;

public interface InvalidTokenServiceInterface {
    void deleteExpiryTimeToken();
}
