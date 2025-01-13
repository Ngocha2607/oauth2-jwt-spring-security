package com.springboot.eCommerce.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    EXISTED_USER(1001, "User existed"),
    USERNAME_INVALID(1002, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),
    MESSAGE_KEY_INVALID(1004, "Invalid message key"),
    USER_NOT_FOUND(1001, "User Not Found"),
    ;
    private int code;
    private String message;

    ErrorCode( int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
