package com.example.ToDoList.service;

import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;

public interface TaskService {

    TaskEntity findById(Integer id);
    TaskEntity finsByOwner(UserEntity owner);

    void deleteById(Integer id);
    void deleteByOwner(UserEntity owner);

    void create(TaskEntity entity);
}
