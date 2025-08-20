package com.example.ToDoList.repository;

import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findById(Integer id);
    Optional<TaskEntity> findByOwner(UserEntity owner);

    void deleteById(Integer id);
    void deleteByOwner(UserEntity owner);
}

