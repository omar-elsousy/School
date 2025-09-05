package com.example.spring_boot_start.config;

import com.example.spring_boot_start.controller.vm.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(Exception.class)
   public ResponseEntity<ExceptionResponse> handleRuntimeException (Exception exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(List.of(exception.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationEx(MethodArgumentNotValidException ex){
        // String message =  ex.getBindingResult().getFieldError().getDefaultMessage();
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error-> error.getDefaultMessage()).toList();
        return ResponseEntity.badRequest().body(new ExceptionResponse(errors));
    }
}