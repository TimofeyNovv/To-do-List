package com.example.ToDoList.service;

import com.example.ToDoList.dto.task.TaskDescUpdateDto;
import com.example.ToDoList.dto.task.TaskResponseDto;
import com.example.ToDoList.dto.task.TaskStatusUpdateDto;
import com.example.ToDoList.dto.task.TaskTitleUpdateDto;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;

public interface TaskService {

    TaskResponseDto findById(Integer id);
    TaskResponseDto finsByOwner(UserEntity owner);

    void deleteById(Integer id);
    void deleteByOwner(UserEntity owner);

    void create(TaskEntity entity);

    void updateDescription(TaskDescUpdateDto request);
    void updateTitle(TaskTitleUpdateDto request);
    void updateStatus(TaskStatusUpdateDto request);
}
