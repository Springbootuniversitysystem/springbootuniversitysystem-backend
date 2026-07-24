package com.smartcareer.exception;

public class ExpiredCodeException extends RuntimeException
{
    public ExpiredCodeException(String message) {
        super(message);
    }
}
