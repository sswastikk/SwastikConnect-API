package com.software.swastik.project.swastik.connect.exceptions;

import com.software.swastik.project.swastik.connect.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptonHandler(ResourceNotFoundException ex)
    {
        return new ResponseEntity(new ApiResponse(ex.getMessage(), false, "Not Found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String, String> errorCodes = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorCodes.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(errorCodes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> apiExceptionHandler(ApiException ex)
    {
        return new ResponseEntity(new ApiResponse(ex.getMessage(), true, "Not Found"), HttpStatus.BAD_REQUEST);
    }
}
