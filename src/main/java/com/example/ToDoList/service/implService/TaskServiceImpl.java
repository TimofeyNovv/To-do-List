package com.example.ToDoList.service.implService;

import com.example.ToDoList.dto.task.TaskDescUpdateDto;
import com.example.ToDoList.dto.task.TaskResponseDto;
import com.example.ToDoList.dto.user.UserSmallInfoDto;
import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.repository.TaskRepository;
import com.example.ToDoList.repository.UserRepository;
import com.example.ToDoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
        if (!repository.existsById(Long.valueOf(id))) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteByOwner(UserEntity owner) {
        if (!repository.existsByOwner(owner)) {
            throw new TaskNotFoundException("Task for owner " + owner.getId() + " not found");
        }
        repository.deleteByOwner(owner);
    }

    @Override
    public void create(TaskEntity entity) {
        repository.save(entity);
    }

    @Override
    public void updateDescription(TaskDescUpdateDto request) {
        Long taskId = request.getTaskId();
        TaskEntity task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));

        task.setDescription(request.getNewDescription());
        repository.save(task);
    }

}
