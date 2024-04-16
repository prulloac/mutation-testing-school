package com.example.demo.exceptions;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {    
    public record BadResponse(String message, String details) {
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<BadResponse> handleItemNotFoundException(ItemNotFoundException e, WebRequest webRequest) {
        return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(new BadResponse(e.getMessage(), webRequest.getDescription(false)));
    }
}
