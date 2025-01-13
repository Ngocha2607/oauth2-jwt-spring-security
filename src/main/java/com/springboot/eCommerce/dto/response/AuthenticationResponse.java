package com.springboot.eCommerce.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    boolean authenticated;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
