package com.sda.weatherservis.exception;

public class NotCorrectBoundryException extends RuntimeException {
    public NotCorrectBoundryException(String message) {
        super("Not Correct Boudry Exception: "+message);
    }

}
