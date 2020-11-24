package ru.croc.coder.controller;

import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ru.croc.coder.service.TaskConstraintException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskConstraintException.class)
    public ResponseEntity<Object> handleProblemConstraintException(TaskConstraintException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionDto(ex.getClass().getName(), ex.getMessage(), new HashMap<String, String>() {{
            put("sessionId", request.getSessionId());
        }}), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}