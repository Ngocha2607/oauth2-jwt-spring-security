package com.springboot.eCommerce.service.impl;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.request.UserUpdationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.entity.Role;
import com.springboot.eCommerce.entity.User;
import com.springboot.eCommerce.exception.AppException;
import com.springboot.eCommerce.exception.ErrorCode;
import com.springboot.eCommerce.repository.RoleRepository;
import com.springboot.eCommerce.repository.UserRepository;
import com.springboot.eCommerce.service.UserServiceInterface;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserServiceInterface {
    RoleRepository roleRepository;
     UserRepository userRepository;
     ModelMapper modelMapper;
     PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<UserResponse> createRequest(UserCreationRequest request) {

        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.EXISTED_USER);
        User user = new User(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = roleRepository.findAllById(request.getRoles());

        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return new ApiResponse<>(userResponse);
    }

//    @PreAuthorize("hasRole('APPROVE_POST')")
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    @Override
    public List<UserResponse> getUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            return modelMapper.map(user, UserResponse.class);
        }).collect(Collectors.toList());
    }
    @PostAuthorize("returnObject.username == authentication.name")
    @Override
    public UserResponse getUser(String userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findUserByUsername(name).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_FOUND));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(String userId, UserUpdationRequest request) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));

            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setDob(request.getDob());
        List<Role> roles = roleRepository.findAllById(request.getRoles());

        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
            return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
