package com.example.demo.exception;



import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class)

    public ResponseEntity<ErrorResponse>
    handleResourceNotFoundException(

            ResourceNotFoundException ex,

            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.NOT_FOUND.value(),

                "NOT_FOUND",

                ex.getMessage(),

                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(
            DuplicateResourceException.class)

    public ResponseEntity<ErrorResponse>
    handleDuplicateResourceException(

            DuplicateResourceException ex,

            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.CONFLICT.value(),

                "DUPLICATE_RESOURCE",

                ex.getMessage(),

                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(
            InvalidRequestException.class)

    public ResponseEntity<ErrorResponse>
    handleInvalidRequestException(

            InvalidRequestException ex,

            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.BAD_REQUEST.value(),

                "BAD_REQUEST",

                ex.getMessage(),

                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorResponse>
    handleGlobalException(

            Exception ex,

            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.INTERNAL_SERVER_ERROR.value(),

                "INTERNAL_SERVER_ERROR",

                ex.getMessage(),

                request.getRequestURI()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}