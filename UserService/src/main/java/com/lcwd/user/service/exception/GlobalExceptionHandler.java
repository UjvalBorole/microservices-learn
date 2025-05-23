package com.lcwd.user.service.exception;

import com.lcwd.user.service.payLoad.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse res = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new  ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
    }
}
