package com.example.coffee_crud.exception;

public class CoffeeNotFoundException extends RuntimeException {
    public CoffeeNotFoundException(String message) {
        super(message);
    }
}
