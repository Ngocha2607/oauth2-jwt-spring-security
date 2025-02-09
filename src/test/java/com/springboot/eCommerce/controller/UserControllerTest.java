package com.springboot.eCommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.RoleResponse;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.service.UserServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserServiceInterface userServiceInterface;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private LocalDate dob;


    @BeforeEach
    void initData() {
        dob = LocalDate.of(2000, 1, 1);
        Set<String> roles = new HashSet<>();
        Set<RoleResponse> rolesResponse = new HashSet<>();
        request = UserCreationRequest.builder()
                .username("test1")
                .firstName("Test")
                .lastName("Test")
                .password("12345678")
                .dob(dob)
                .roles(roles)
                .build();

        userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .username("test")
                .firstName("Test")
                .lastName("Test")
                .dob(dob)
                .roles(rolesResponse)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
//        GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String content = objectMapper.writeValueAsString(request);

        Mockito.when(userServiceInterface.createRequest(ArgumentMatchers.any()))
                .thenReturn(new ApiResponse<UserResponse>(userResponse));
//        WHEN, THEN

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users/store")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id")
                        .value("cf0600f538b"));
    }

    @Test
    void createUser_invalidUsername_fail() throws Exception {
//        GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String content = objectMapper.writeValueAsString(request);

//        WHEN, THEN

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/store")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value(1002))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Username must be at least 3 characters"));
    }
}
