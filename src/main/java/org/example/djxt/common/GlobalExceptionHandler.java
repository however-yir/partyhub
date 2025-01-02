package org.example.djxt.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, Object> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "BAD_REQUEST");
        body.put("message", ex.getMessage());
        return body;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleGeneral(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", "INTERNAL_ERROR");
        body.put("message", ex.getMessage());
        return body;
    }
}
