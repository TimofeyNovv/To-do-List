package com.example.ToDoList.service;

import com.example.ToDoList.dto.task.*;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;

public interface TaskService {

    TaskResponseDto findById(Integer id);
    TaskResponseDto finsByOwner(UserEntity owner);

    void deleteById(Integer id);
    void deleteByOwner(UserEntity owner);

    void create(TaskCreateDto request);

    void updateDescription(TaskDescUpdateDto request);
    void updateTitle(TaskTitleUpdateDto request);
    void updateStatus(TaskStatusUpdateDto request);
}
