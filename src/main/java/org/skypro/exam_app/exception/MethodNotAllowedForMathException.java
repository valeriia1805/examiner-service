package org.skypro.exam_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedForMathException extends RuntimeException {
    public MethodNotAllowedForMathException(String op) {
        super("Math questions do not support operation: " + op);
    }
}
