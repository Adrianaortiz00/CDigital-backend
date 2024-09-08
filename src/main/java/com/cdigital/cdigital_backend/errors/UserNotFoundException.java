package com.cdigital.cdigital_backend.errors;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
      }
}
