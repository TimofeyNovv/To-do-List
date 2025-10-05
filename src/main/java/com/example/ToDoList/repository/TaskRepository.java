package com.example.ToDoList.repository;

import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    Optional<TaskEntity> findById(Integer id);
    List<TaskEntity> findByOwner(UserEntity owner);


    void deleteById(Integer id);
    void deleteByOwner(UserEntity owner);

    boolean existsByOwner(UserEntity owner);
    boolean existsByTitle(String title);
}

