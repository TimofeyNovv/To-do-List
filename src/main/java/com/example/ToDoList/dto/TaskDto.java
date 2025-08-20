package com.example.ToDoList.dto;

import com.example.ToDoList.model.entity.task.StatusTask;

public class TaskDto {

    private String title;
    private String description;
    private StatusTask status;
    private UserDto owner;
}
