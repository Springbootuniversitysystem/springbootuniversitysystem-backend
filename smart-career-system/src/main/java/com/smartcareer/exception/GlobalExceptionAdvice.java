package com.smartcareer.exception;

import com.smartcareer.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/*
 * This class sends the error response
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // 404 - Learner not found
    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity<Response<Void>> handleLearnerNotFound(LearnerNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error(404,ex.getMessage()));
    }
    // 400 - Bean Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Void>> handleValidation(MethodArgumentNotValidException ex) {

        String message = Objects.requireNonNull(ex.getBindingResult()
                        .getFieldError())
                .getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.error(400, message));
    }
    // Email exists
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Response<Void>> handleEmailAlreadyExist(EmailAlreadyExistException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.error(404,ex.getMessage()));
    }
    //Inactive account
    @ExceptionHandler(InactiveAccountException.class)
    public ResponseEntity<Response<Void>> handleInactiveAccount(
            InactiveAccountException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.error(400, ex.getMessage()));
    }
     //For wrong credentials
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response<Void>> handleInvalidCredentials(
            InvalidCredentialsException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.error(400, ex.getMessage()));
    }
    @ExceptionHandler(CareerNotFoundException.class)
    public ResponseEntity<Response<Void>> handleCareerNotFound(CareerNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(404, ex.getMessage()));
    }

    @ExceptionHandler(UniversityProgrammeNotFoundEx.class)
    public ResponseEntity<Response<Void>> handleUniversityProgrammeNotFound(UniversityProgrammeNotFoundEx ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(404, ex.getMessage()));
    }
}
