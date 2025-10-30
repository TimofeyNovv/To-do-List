package com.example.ToDoList.service;

import com.example.ToDoList.dto.user.UserResponseDto;
import com.example.ToDoList.dto.user.UserSmallInfoDto;
import com.example.ToDoList.model.entity.user.UserEntity;

public interface UserService {

    UserResponseDto findById(Integer id);
    UserResponseDto findByEmail(String email);

    void deleteUserById(Integer id);
    void deleteUserByEmail(String email);

    UserSmallInfoDto getCurrentUserInfo(UserEntity currentUser);
}
