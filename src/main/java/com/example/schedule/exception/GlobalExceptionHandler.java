package com.example.schedule.exception;

import com.example.schedule.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handlePasswordMismatch(PasswordMismatchException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage(), 403);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(NotFoundException ex){
        ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}