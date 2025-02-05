package com.springboot.eCommerce.service.impl;

import com.springboot.eCommerce.dto.request.PermissionCreationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.PermissionResponse;
import com.springboot.eCommerce.entity.Permission;
import com.springboot.eCommerce.repository.InvalidatedTokenRepository;
import com.springboot.eCommerce.repository.PermissionRepository;
import com.springboot.eCommerce.service.InvalidTokenServiceInterface;
import com.springboot.eCommerce.service.PermissionServiceInterface;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvalidTokenServiceImpl implements InvalidTokenServiceInterface {
    InvalidatedTokenRepository invalidatedTokenRepository;

    @Override
    public void deleteExpiryTimeToken() {
        invalidatedTokenRepository.deleteAllExpiryTimeToken(new Date());
    }
}
