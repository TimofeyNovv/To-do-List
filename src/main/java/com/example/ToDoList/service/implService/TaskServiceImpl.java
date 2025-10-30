package com.example.ToDoList.service.implService;

import com.example.ToDoList.dto.task.*;
import com.example.ToDoList.dto.user.UserSmallInfoDto;
import com.example.ToDoList.exception.NoAccessException;
import com.example.ToDoList.exception.TaskAlreadyExistsException;
import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.exception.UserNotFoundException;
import com.example.ToDoList.model.entity.task.StatusTask;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.repository.TaskRepository;
import com.example.ToDoList.repository.UserRepository;
import com.example.ToDoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto findById(Integer id, UserEntity currentUser) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id - " + id + " not found"));

        if (!currentUser.getEmail().equals(task.getOwner().getEmail())) {
            throw new NoAccessException("User with email - " + currentUser.getEmail() + "does not have access rights to this task");
        }

        UserSmallInfoDto owner = UserSmallInfoDto.builder()
                .name(currentUser.getName())
                .email(currentUser.getEmail())
                .role(currentUser.getRole())
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
    public List<TaskSmallInfoDto> findByOwner(UserEntity owner) {
        List<TaskEntity> tasks = repository.findByOwner(owner);

        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found for owner: " + owner.getId());
        }

        return tasks.stream()
                .map(task -> TaskSmallInfoDto.builder()
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .status(task.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id, UserEntity currentUser) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        if (!currentUser.getEmail().equals(task.getOwner().getEmail())) {
            throw new NoAccessException("User with email - " + currentUser.getEmail() + "does not have access rights to this task");
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
    public void create(TaskCreateDto request, UserEntity currentUser) {
        TaskEntity entity = TaskEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .owner(currentUser)
                .status(StatusTask.valueOf(request.getStatus().toUpperCase()))
                .build();
        repository.save(entity);
    }

    @Override
    public void updateDescription(TaskDescUpdateDto request, UserEntity currentUser) {
        Long taskId = request.getTaskId();
        TaskEntity task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));

        if (!currentUser.getEmail().equals(task.getOwner().getEmail())){
            throw new NoAccessException("User with email - " + currentUser.getEmail() + "does not have access rights to this task");
        }
        task.setDescription(request.getNewDescription());
        repository.save(task);
    }

    @Override
    public void updateTitle(TaskTitleUpdateDto request, UserEntity currentUser) {
        Long taskId = request.getTaskId();
        TaskEntity task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));
        if (!currentUser.getEmail().equals(task.getOwner().getEmail())){
            throw new NoAccessException("User with email - " + currentUser.getEmail() + "does not have access rights to this task");
        }
        task.setTitle(request.getNewTitle());
        repository.save(task);
    }

    @Override
    public void updateStatus(TaskStatusUpdateDto request, UserEntity currentUser) {
        Long taskId = request.getTaskId();
        TaskEntity task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));
        if (!currentUser.getEmail().equals(task.getOwner().getEmail())){
            throw new NoAccessException("User with email - " + currentUser.getEmail() + "does not have access rights to this task");
        }
        task.setStatus(StatusTask.valueOf(request.getStatus().toUpperCase()));
        repository.save(task);
    }

}
