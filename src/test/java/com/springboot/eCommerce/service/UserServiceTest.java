package com.springboot.eCommerce.service;

import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.response.RoleResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.entity.Role;
import com.springboot.eCommerce.entity.User;
import com.springboot.eCommerce.exception.AppException;
import com.springboot.eCommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2000, 1, 1);
        Set<String> rolesRequest = new HashSet<>();
        Set<RoleResponse> rolesResponse = new HashSet<>();
        Set<Role> roles = new HashSet<>();

        userCreationRequest = UserCreationRequest.builder()
                .username("test")
                .firstName("Test")
                .lastName("Test")
                .password("12345678")
                .dob(dob)
                .roles(rolesRequest)
                .build();

        userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .username("test")
                .firstName("Test")
                .lastName("Test")
                .dob(dob)
                .roles(rolesResponse)
                .build();

        user = User.builder()
                .id("cf0600f538b3")
                .username("test")
                .firstName("Test")
                .lastName("Test")
                .dob(dob)
                .roles(roles)
                .build();
    }

    @Test
    void create_validRequest_success() {
//        GIVEN
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

//        WHEN
        var response = userServiceInterface.createRequest(userCreationRequest);

//        THEN
        assertThat(response.getResult()).isEqualTo(userResponse);
    }

    @Test
    void createUser_invalidExisted_fail() {
//        GIVEN
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(true);

//        WHEN
        var exception = assertThrows(AppException.class,
                () -> userServiceInterface.createRequest(userCreationRequest));

//        THEN

        assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);
    }
}
