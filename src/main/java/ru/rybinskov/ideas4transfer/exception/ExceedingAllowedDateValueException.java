package ru.rybinskov.ideas4transfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class ExceedingAllowedDateValueException extends Exception {
    public ExceedingAllowedDateValueException(String message) {
        super(message);
    }
}
