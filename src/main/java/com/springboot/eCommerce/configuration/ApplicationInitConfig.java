package com.springboot.eCommerce.configuration;

import com.springboot.eCommerce.common.Role;
import com.springboot.eCommerce.dto.request.UserCreationRequest;
import com.springboot.eCommerce.dto.response.UserResponse;
import com.springboot.eCommerce.entity.User;
import com.springboot.eCommerce.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(!userRepository.existsByUsername("admin")) {
                HashSet<String> roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                UserCreationRequest userCreationRequest = UserCreationRequest
                        .builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();

                User user = new User(userCreationRequest);

                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin, please change it");
            }
        };
    }

}