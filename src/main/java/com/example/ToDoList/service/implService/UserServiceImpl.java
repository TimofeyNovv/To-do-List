package com.example.ToDoList.service.implService;

import com.example.ToDoList.dto.TaskSmallInfoDto;
import com.example.ToDoList.dto.UserResponseDto;
import com.example.ToDoList.exception.UserNotFoundException;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.repository.UserRepository;
import com.example.ToDoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Integer id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id - " + id + " not found"));
        return UserResponseDto.builder().name(user.getName())
                .role(user.getRole())
                .email(user.getEmail())
                .tasks(user.getTasks()
                        .stream()
                        .map(task -> TaskSmallInfoDto.builder()
                                .title(task.getTitle())
                                .description(task.getDescription())
                                .status(task.getStatus())
                                .build())
                        .collect(Collectors.toList()))
                .build();

    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findByEmail(String email) {
        UserEntity user = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email -" + email + " not found"));

        return UserResponseDto.builder().name(user.getName())
                .role(user.getRole())
                .email(user.getEmail())
                .tasks(user.getTasks()
                        .stream()
                        .map(task -> TaskSmallInfoDto.builder()
                                .title(task.getTitle())
                                .description(task.getDescription())
                                .status(task.getStatus())
                                .build())
                        .collect(Collectors.toList()))
                .build();

    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }
}
