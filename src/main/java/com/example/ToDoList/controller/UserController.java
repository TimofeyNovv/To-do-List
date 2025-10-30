package com.example.ToDoList.controller;

import com.example.ToDoList.dto.ErrorResponse;
import com.example.ToDoList.dto.user.UserResponseDto;
import com.example.ToDoList.exception.UserNotFoundException;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.service.implService.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "API для управления пользователями")
@SecurityRequirement(name = "jwtAuth")
public class UserController {

    private final UserServiceImpl service;


    @Operation(
            summary = "Получить пользователя по его email",
            description = "Возвращает пользователя по указанному email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача найдена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @Operation(
            summary = "Получить пользователя по его id",
            description = "Возвращает пользователя по указанному id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача найдена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }


    @Operation(
            summary = "Удалить пользователя по его id",
            description = "Удаляет пользователя по его id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Успешно удаленно"),
                    @ApiResponse(responseCode = "409", description = "Нельзя удалить пользователя с правами админа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Удалить пользователя по его email",
            description = "Удаляет пользователя по его email",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Успешно удаленно"),
                    @ApiResponse(responseCode = "409", description = "Нельзя удалить пользователя с правами админа", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping("/delete/email/{email}")
    public ResponseEntity<?> deleteByEmail(@PathVariable String email) {
        service.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "получить информацию о текущем пользвоателе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "успешно")
            }
    )
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserInfo(@AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(service.getCurrentUserInfo(currentUser));
    }
}
