package com.example.ToDoList.service;

import com.example.ToDoList.dto.TaskDto;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;

public interface TaskService {

    TaskDto findById(Integer id);
    TaskDto finsByOwner(UserEntity owner);

    void deleteById(Integer id);
    void deleteByOwner(UserEntity owner);

    void create(TaskEntity entity);
}
