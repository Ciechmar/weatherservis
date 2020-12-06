package com.sda.weatherservis.exception;

public class JasonParseException extends RuntimeException{
    public JasonParseException(String message) {

        super("Jason problem: " + message);
    }
}
