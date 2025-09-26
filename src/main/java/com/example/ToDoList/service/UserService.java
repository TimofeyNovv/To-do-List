package com.example.ToDoList.service;

import com.example.ToDoList.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto findById(Integer id);
    UserResponseDto findByEmail(String email);

    void deleteUserById(Integer id);
    void deleteUserByEmail(String email);
}
