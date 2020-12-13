package com.sda.weatherservis.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("Not Found: " + message);
    }
}
