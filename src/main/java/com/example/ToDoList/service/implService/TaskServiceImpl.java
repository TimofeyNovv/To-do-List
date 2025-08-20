package com.example.ToDoList.service.implService;

import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.repository.TaskRepository;
import com.example.ToDoList.repository.UserRepository;
import com.example.ToDoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    @Override
    public TaskEntity findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id - " + id + " not found"));
    }

    @Override
    public TaskEntity finsByOwner(UserEntity owner) {
        return repository.findByOwner(owner)
                .orElseThrow(() -> new TaskNotFoundException("Task with owner - " + owner + " not found"));
    }

    @Override
    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    @Override
    public void deleteByOwner(UserEntity owner) {
        repository.deleteByOwner(owner);
    }

    @Override
    public void create(TaskEntity entity) {
        entity.setOwner(userRepository.getById(4));
        repository.save(entity);
    }
}
