package com.example.ToDoList.dto.user;

import com.example.ToDoList.dto.task.TaskSmallInfoDto;
import com.example.ToDoList.model.entity.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO с краткими данными о пользователями для ответа")
public class UserResponseDto {

    private String name;
    private String email;
    private Role role;
    List<TaskSmallInfoDto> tasks;

}
