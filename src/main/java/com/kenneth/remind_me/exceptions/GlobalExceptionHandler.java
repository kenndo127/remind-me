package com.kenneth.remind_me.exceptions;


import com.kenneth.remind_me.dto.response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseWrapper<Void> handleResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseWrapper.<Void>builder()
                .data(null)
                .response(ex.getMessage())
                .build();
    }

    @ExceptionHandler(DuplicatePersonFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseWrapper<Void> handleDuplicatePersonFoundException(DuplicatePersonFoundException ex){
        return ResponseWrapper.<Void>builder()
                .data(null)
                .response(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<Map<String, String>> handleValidationErrorExceptions(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseWrapper.<Map<String, String>>builder()
                .data(errors)
                .response("Validation Failed")
                .build();
    }
}
