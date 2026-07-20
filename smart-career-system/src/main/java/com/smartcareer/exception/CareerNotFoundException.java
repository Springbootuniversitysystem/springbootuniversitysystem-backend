package com.smartcareer.exception;

public class CareerNotFoundException extends RuntimeException {
    public CareerNotFoundException(String message) {
        super(message);
    }
}
