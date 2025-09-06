package com.example.ToDoList.dto;

import com.example.ToDoList.model.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String name;
    private String email;
    private Role role;
    List<TaskSmallInfoDto> tasks;
}
