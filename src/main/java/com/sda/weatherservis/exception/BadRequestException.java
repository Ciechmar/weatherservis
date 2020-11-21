package com.sda.weatherservis.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super("Bad Request: "+message);
    }
}
