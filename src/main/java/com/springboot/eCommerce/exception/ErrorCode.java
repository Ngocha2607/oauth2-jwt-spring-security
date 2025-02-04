package com.springboot.eCommerce.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    EXISTED_USER(1001, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    MESSAGE_KEY_INVALID(1004, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1005, "User Not Found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You don't have permission", HttpStatus.FORBIDDEN),
    PERMISSION_NAME_INVALID(1008, "Permission name must be at least {min} characters", HttpStatus.BAD_REQUEST),
    ROLE_NAME_INVALID(1009, "Role name must be at least {min} characters", HttpStatus.BAD_REQUEST),
    DOB_INVALID(1010, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
    ErrorCode( int code, String message, HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
