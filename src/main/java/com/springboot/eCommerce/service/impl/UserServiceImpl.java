package com.springboot.eCommerce.service.impl;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.request.UserUpdationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.entity.User;
import com.springboot.eCommerce.exception.AppException;
import com.springboot.eCommerce.exception.ErrorCode;
import com.springboot.eCommerce.repository.UserRepository;
import com.springboot.eCommerce.service.UserServiceInterface;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserServiceInterface {
     UserRepository userRepository;
     ModelMapper modelMapper;
     PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<UserResponse> createRequest(UserCreationRequest request) {

        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.EXISTED_USER);
        User user = new User(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return new ApiResponse<>(userResponse);
    }

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            return modelMapper.map(user, UserResponse.class);
        }).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(String userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(String userId, UserUpdationRequest request) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));

            user.setPassword(request.getPassword());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setDob(request.getDob());
        userRepository.save(user);
            return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
