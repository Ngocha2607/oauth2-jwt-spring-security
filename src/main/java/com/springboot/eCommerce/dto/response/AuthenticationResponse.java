package com.springboot.eCommerce.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;
    boolean authenticated;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(boolean authenticated, String token) {
        this.authenticated = authenticated;
        this.token = token;
    }
}
