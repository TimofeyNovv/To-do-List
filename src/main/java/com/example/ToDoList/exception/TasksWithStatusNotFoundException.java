package com.example.ToDoList.exception;

public class TasksWithStatusNotFoundException extends RuntimeException {
    public TasksWithStatusNotFoundException(String message) {
        super(message);
    }
}
