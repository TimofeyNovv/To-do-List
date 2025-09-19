package com.example.ToDoList.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "email не может быть пустым")
    @Email(message = "Некорректный формат email")
    @Schema(description = "Email пользователя")
    private String email;

    @NotBlank(message = "пароль не может быть пустым")
    @Schema(description = "пароль пользователя")
    String password;
}
