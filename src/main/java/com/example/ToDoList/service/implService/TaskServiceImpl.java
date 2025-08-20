package com.example.ToDoList.service.implService;

import com.example.ToDoList.dto.TaskDto;
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
    public TaskDto findById(Integer id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id - " + id + " not found"));
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus()).build();
    }

    @Override
    public TaskDto finsByOwner(UserEntity owner) {
        TaskEntity task =  repository.findByOwner(owner)
                .orElseThrow(() -> new TaskNotFoundException("Task with owner - " + owner + " not found"));
        return TaskDto.builder()
                .status(task.getStatus())
                .title(task.getTitle())
                .description(task.getDescription())
                .build();
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
        //entity.setOwner(userRepository.getById(4));
        repository.save(entity);
    }
}
