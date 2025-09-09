package com.example.ToDoList.service.implService;

import com.example.ToDoList.dto.TaskResponseDto;
import com.example.ToDoList.dto.UserSmallInfoDto;
import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.repository.TaskRepository;
import com.example.ToDoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Override
    public TaskResponseDto findById(Integer id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id - " + id + " not found"));


        UserEntity ownerEntity = task.getOwner();
        UserSmallInfoDto owner = UserSmallInfoDto.builder()
                .name(ownerEntity.getName())
                .email(ownerEntity.getEmail())
                .role(ownerEntity.getRole())
                .build();

        return TaskResponseDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .owner(owner)
                .build();
    }

    @Override
    public TaskResponseDto finsByOwner(UserEntity owner) {
        TaskEntity task = repository.findByOwner(owner)
                .orElseThrow(() -> new TaskNotFoundException("Task with owner - " + owner + " not found"));

        UserSmallInfoDto ownerDto = UserSmallInfoDto.builder()
                .name(owner.getName())
                .email(owner.getEmail())
                .role(owner.getRole())
                .build();

        return TaskResponseDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .owner(ownerDto)
                .build();
    }

    @Override
    public void deleteById(Integer id) {
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
