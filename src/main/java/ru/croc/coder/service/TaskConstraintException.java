package ru.croc.coder.service;

public class TaskConstraintException extends RuntimeException{
    public TaskConstraintException() {
        super();
    }

    public TaskConstraintException(String message) {
        super(message);
    }
}
