package com.cdigital.cdigital_backend.errors;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
      }
}
