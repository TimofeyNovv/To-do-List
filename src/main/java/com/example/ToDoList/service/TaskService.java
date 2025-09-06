package com.example.ToDoList.service;

import com.example.ToDoList.dto.TaskResponseDto;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;

public interface TaskService {

    TaskResponseDto findById(Integer id);
    TaskResponseDto finsByOwner(UserEntity owner);

    void deleteById(Integer id);
    void deleteByOwner(UserEntity owner);

    void create(TaskEntity entity);
}
