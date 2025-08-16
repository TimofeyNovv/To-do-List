package com.example.ToDoList.service;

import com.example.ToDoList.model.entity.user.UserEntity;

public interface UserService {

    UserEntity findById(Integer id);
    UserEntity findByEmail(String email);

    void deleteUserById(Integer id);
    void deleteUserByEmail(String email);
}
