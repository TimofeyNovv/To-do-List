package com.example.ToDoList.controller;

import com.example.ToDoList.dto.user.UserResponseDto;
import com.example.ToDoList.exception.UserNotFoundException;
import com.example.ToDoList.service.implService.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtAuth")
public class UserController {

    private final UserServiceImpl service;


    @Operation(
            summary = "Получить пользователя по его email",
            description = "Возвращает пользователя по указанному email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача найдена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(service.findByEmail(email));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Получить пользователя по его id",
            description = "Возвращает пользователя по указанному id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача найдена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(
            summary = "Удалить пользователя по его id",
            description = "Удаляет пользователя по его id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Успешно удаленно"),
                    @ApiResponse(responseCode = "409", description = "Нельзя удалить пользователя с правами адмна"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
            //security = @SecurityRequirement(name = "jwtAuth")
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
                    @ApiResponse(responseCode = "409", description = "Нельзя удалить пользователя с правами адмна"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    @DeleteMapping("/delete/email/{email}")
    public ResponseEntity<?> deleteByEmail(@PathVariable String email) {
        service.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
