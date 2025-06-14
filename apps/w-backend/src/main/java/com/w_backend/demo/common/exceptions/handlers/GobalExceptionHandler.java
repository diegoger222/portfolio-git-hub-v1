package com.w_backend.demo.common.exceptions.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.w_backend.demo.common.exceptions.custom_errors.errors.CustomException;
import com.w_backend.demo.common.utils.AnsiColor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException ex) {
        // This map will hold the response body
        // We don't want to expose sensitive information, so we only include necessary
        // details, like code, status, and sort error message.
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("http status", ex.getStatusCode().value());
        body.put("internal code", ex.getCode());
        // body.put("error", ex.getModule()); we don't want to expose the module
        // body.put("message", ex.getMessage()); we can't expose the full message

        // Log the exception for debugging purposes
        log.error("--------------------------------");
        log.error("CustomException occurred: {}", ex.getMessage());
        log.error("timestamp: {}", LocalDateTime.now());
        log.error("HTTP Status: {}", ex.getStatusCode().value());
        log.error("Internal Code: {}", ex.getCode());
        log.error("Error Module: {}", ex.getModule());
        log.error("Error:", ex);

        // Return the response entity with the body and status code
        return new ResponseEntity<>(body, ex.getStatusCode());
    }

    // This is an example of a global exception handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
