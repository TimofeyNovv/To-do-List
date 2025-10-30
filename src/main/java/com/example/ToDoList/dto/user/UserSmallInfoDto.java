package com.example.ToDoList.dto.user;

import com.example.ToDoList.model.entity.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO с краткими данными о пользователями для ответа")
public class UserSmallInfoDto {

    private String name;
    private String email;
    private Role role;

}
