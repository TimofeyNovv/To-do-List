package com.example.ToDoList.controller;

import com.example.ToDoList.dto.task.*;
import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.service.implService.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Task", description = "API для управления задачами пользователя")
@SecurityRequirement(name = "jwtAuth")
public class TaskController {

    private final TaskServiceImpl service;


    @Operation(
            summary = "получить задачу по её id",
            description = "получает задачу по её id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача найдена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @GetMapping("/id/{id}")
    public ResponseEntity<TaskResponseDto> getById(@AuthenticationPrincipal UserEntity currentUser, @PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id, currentUser));
    }


    @Operation(
            summary = "Удалить задачу по id",
            description = "Удаляет задачу по её id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача найдена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskEntity> delById(@AuthenticationPrincipal UserEntity user, @PathVariable Integer id) {
        service.deleteById(id,user);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Создать задачу",
            description = "Создаёт задачу",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно создано"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "409", description = "Задача с таким заголовком уже существует"),
                    @ApiResponse(responseCode = "404", description = "Владелец с таким id не найден")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<TaskEntity> create(@AuthenticationPrincipal UserEntity currentUser, @Valid @RequestBody TaskCreateDto request) {
        service.create(request, currentUser);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Обновить описание задачи",
            description = "Обновляет описание задачи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно обновленно"),
                    @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена"),
            }
    )
    @PatchMapping("/update/desc")
    public ResponseEntity<TaskCreateDto> updateDescription(@AuthenticationPrincipal UserEntity currentUser, @RequestBody TaskDescUpdateDto request) {
        service.updateDescription(request, currentUser);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "обновить заголовок задачи",
            description = "обноволяет заголовок задачи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно обновленно"),
                    @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена"),
            }
    )
    @PatchMapping("update/title")
    public ResponseEntity<TaskCreateDto> updateTitle(@AuthenticationPrincipal UserEntity currentUser, @RequestBody TaskTitleUpdateDto request) {
        service.updateTitle(request, currentUser);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "обновить статус задачи",
            description = "обновляет статус задачи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно обновленно"),
                    @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена"),
                    @ApiResponse(responseCode = "400", description = "Невалидный статус задачи")
            }
    )
    @PatchMapping("update/status")
    public ResponseEntity<TaskCreateDto> updateStatus(@AuthenticationPrincipal UserEntity currentUser, @Valid @RequestBody TaskStatusUpdateDto request) {
        service.updateStatus(request, currentUser);
        return ResponseEntity.noContent().build();
    }
}
