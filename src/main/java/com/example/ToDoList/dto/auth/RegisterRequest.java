package com.example.ToDoList.dto.auth;

import com.example.ToDoList.model.entity.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно содкрдать от 5 до 30 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s]+$", message = "Имя может содержать только буквы и пробелы")
    @Schema(description = "Имя пользователя")
    private String name;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некоорректный формат email")
    @Schema(description = "Email пользователя")
    private String email;

    @NotBlank(message = "пароль не может быть пустым")
    @Size(min = 8, max = 30, message = "Пароль должен содерджать от 8 до 30 символов")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву и одну цифру")
    @Schema(description = "Пароль пользователя")
    private String password;

    @NotNull(message = "Роль не может отсутствовать")
    @Pattern(regexp = "USER|ADMIN", message = "Недопустимое значение роли. Допустимые значения: USER, ADMIN")
    @Schema(description = "Роль пользователя", allowableValues = {"USER", "ADMIN"})
    private String role;


}

