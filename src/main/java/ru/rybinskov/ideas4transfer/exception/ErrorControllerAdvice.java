package ru.rybinskov.ideas4transfer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
//@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(Exception exception, Model model){
        String errorMessage = (exception != null ? exception.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        log.warn("{}", errorMessage);
        return "error";
    }
}
