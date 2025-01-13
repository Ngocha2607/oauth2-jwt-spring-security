package com.springboot.eCommerce.service.impl;

import com.springboot.eCommerce.dto.request.AuthenticationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.AuthenticationResponse;
import com.springboot.eCommerce.entity.User;
import com.springboot.eCommerce.exception.AppException;
import com.springboot.eCommerce.exception.ErrorCode;
import com.springboot.eCommerce.repository.UserRepository;
import com.springboot.eCommerce.service.AuthenticationServiceInterface;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationServiceInterface {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Override
    public ApiResponse<AuthenticationResponse> login(AuthenticationRequest request) {
        User user = userRepository.findUserByUsername(request.getUsername()).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(authenticated);
        return new ApiResponse<>(authenticationResponse);
    }
}
