package com.springboot.eCommerce.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectRequest {
    String token;

    public IntrospectRequest() {
    }

    public IntrospectRequest(String token) {
        this.token = token;
    }
}
