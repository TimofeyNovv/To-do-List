package com.example.ToDoList.repository;

import com.example.ToDoList.model.entity.user.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    @NonNull
    Optional<UserEntity> findById(@NonNull Integer id);


    void deleteByEmail(String email);
    void deleteById(@NonNull Integer id);

}
