package com.kenneth.remind_me.exceptions;


import com.kenneth.remind_me.dto.response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseWrapper<Void> handleResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseWrapper.<Void>builder()
                .data(null)
                .response("Resource Not Found")
                .build();
    }
}
