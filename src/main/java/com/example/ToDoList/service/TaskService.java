package com.example.ToDoList.service;

import com.example.ToDoList.dto.task.*;
import com.example.ToDoList.model.entity.user.UserEntity;

import java.util.List;

public interface TaskService {

    TaskResponseDto findById(Integer id, UserEntity currentUser);
    List<TaskResponseDto> findByOwner(UserEntity owner);

    void deleteById(Integer id, UserEntity currentUser);
    void deleteByOwner(UserEntity owner);

    void create(TaskCreateDto request, UserEntity currentUser);

    void updateDescription(TaskDescUpdateDto request);
    void updateTitle(TaskTitleUpdateDto request);
    void updateStatus(TaskStatusUpdateDto request);
}
