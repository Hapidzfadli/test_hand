package com.hand.train.springboot_demo.exception;

import com.hand.train.springboot_demo.dto.WebResponse;
import com.hand.train.springboot_demo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<WebResponse<Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        WebResponse<Object> response = WebResponse.error(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<WebResponse<Object>> handleNotFoundException(
            ResourceNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        WebResponse<Object> response = WebResponse.error(
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                errors
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}