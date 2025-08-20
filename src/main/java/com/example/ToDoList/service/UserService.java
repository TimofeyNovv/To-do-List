package com.example.ToDoList.service;

import com.example.ToDoList.dto.UserDto;
import com.example.ToDoList.model.entity.user.UserEntity;

public interface UserService {

    UserDto findById(Integer id);
    UserDto findByEmail(String email);

    void deleteUserById(Integer id);
    void deleteUserByEmail(String email);
}
