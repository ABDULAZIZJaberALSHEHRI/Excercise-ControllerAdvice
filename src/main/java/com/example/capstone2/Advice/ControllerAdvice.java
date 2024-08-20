package com.example.capstone2.Advice;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Api.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    //JSON parse error: Unexpected character (',' (code 44)): expected a value
    //not writing double quotations
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //ApiException throw 'teacher not found'
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> ApiException(ApiException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //wrong or error in add-applicatio api
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> NoResourceFoundException(NoResourceFoundException e) {
        String message = e.getMessage();
        return ResponseEntity.status(404).body(new ApiResponse(message));
    }

    //filed in argument validation '@Valid when update application without cost'
    //or when add teacher age under 25
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //duplicate phone number and email when add teacher or student
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> DataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //entity must be not null 'when delete student, all student application requests should be removed,
    //so when student doesn't have requests the student request table will be null.
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ApiResponse> NullPointerException(NullPointerException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //Query did not return a unique result
    @ExceptionHandler(value = IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<ApiResponse> IncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException e) {
        String message=e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //application table not found
    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ApiResponse> InvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        String message=e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

}
