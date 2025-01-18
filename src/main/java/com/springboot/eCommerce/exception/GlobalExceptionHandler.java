package com.springboot.eCommerce.exception;

import com.springboot.eCommerce.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<String>> exceptionHandling(Exception exception) {
        ApiResponse<String> apiResponse = new ApiResponse<>(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(), ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<String>> runtimeExceptionHandling(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse<String> apiResponse = new ApiResponse<>(errorCode.getCode(), errorCode.getMessage());
        return new ResponseEntity<>(apiResponse, errorCode.getHttpStatus());
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse<String>> authorizationDenyException(AuthorizationDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponse<String> apiResponse = new ApiResponse<>(errorCode.getCode(), errorCode.getMessage());
        return new ResponseEntity<>(apiResponse, errorCode.getHttpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<String>> methodArgNotValidationExceptionHandling(MethodArgumentNotValidException exception) {
        String enumKey = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();

        ErrorCode errorCode = ErrorCode.MESSAGE_KEY_INVALID;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {

        }
        ApiResponse<String> apiResponse = new ApiResponse<>(errorCode.getCode(), errorCode.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
