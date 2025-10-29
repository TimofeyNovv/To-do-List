package com.example.ToDoList.exception;

public class NoAccessException extends RuntimeException {
    public NoAccessException(String message) {
        super(message);
    }
}
