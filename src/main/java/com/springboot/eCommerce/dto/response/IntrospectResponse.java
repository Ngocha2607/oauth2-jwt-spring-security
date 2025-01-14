package com.springboot.eCommerce.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectResponse {
    boolean valid;

    public IntrospectResponse() {
    }

    public IntrospectResponse(boolean valid) {
        this.valid = valid;
    }
}
