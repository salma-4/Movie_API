package com.movie_api.exception.handler;


import com.movie_api.exception.ConflictException;
import com.movie_api.exception.ExceptionResponse;
import com.movie_api.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<ExceptionResponse> handleConflictException(ConflictException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleRecordNotFoundException(RecordNotFoundException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {IllegalAccessException.class})
    public ResponseEntity<ExceptionResponse> handleIllegalAccessException(IllegalAccessException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}
