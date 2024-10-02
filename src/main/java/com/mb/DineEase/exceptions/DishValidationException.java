package com.mb.DineEase.exceptions;

public class DishValidationException extends RuntimeException {
    public DishValidationException(String message) {
        super(message);
    }
}
