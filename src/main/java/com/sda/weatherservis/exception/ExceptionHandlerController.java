package com.sda.weatherservis.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badRequestHandler(BadRequestException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(JasonParseException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    void jasonParseExceptionHandler (JasonParseException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(NotCorrectBoundryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void notCorrectBoundaryHandler(NotCorrectBoundryException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void notFoundExceptionHandler(NotFoundException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void runtimeExceptionHandler(RuntimeException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    void jsonProcessingExceptionHandler(JsonProcessingException exception) {
        log.error(exception.getMessage());
    }
}
