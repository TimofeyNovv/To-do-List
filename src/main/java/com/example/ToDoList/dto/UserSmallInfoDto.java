package com.example.ToDoList.dto;

import com.example.ToDoList.model.entity.user.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSmallInfoDto {

    private String name;
    private String email;
    private Role role;

}
