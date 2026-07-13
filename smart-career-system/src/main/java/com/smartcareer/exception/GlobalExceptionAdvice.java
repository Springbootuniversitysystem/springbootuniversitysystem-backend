package com.smartcareer.exception;

import com.smartcareer.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * This class sends the error response
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // 404 - Learner not found
    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity<Response<Void>> handleCategoryNotFound(LearnerNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error(404,ex.getMessage()));
    }
}
