package com.example.ToDoList.controller;

import com.example.ToDoList.dto.task.*;
import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.service.implService.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
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
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(
            summary = "Удалить задачу по id",
            description = "Удаляет задачу по её id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача найдена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
            //security = @SecurityRequirement(name = "jwtAuth")
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskEntity> delById(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(
            summary = "Создать задачу",
            description = "Создаёт задачу",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно созданно"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "409", description = "Задача с таким заголовком уже существует"),
                    @ApiResponse(responseCode = "404", description = "Владелец с таким id не найден")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<TaskEntity> create(@Valid @RequestBody TaskCreateDto request) {
        service.create(request);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Обновить описание задачи",
            description = "Обновляет описнаие задачи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно обновленно"),
                    @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена"),
            }
    )
    @PatchMapping("/update/desc")
    public ResponseEntity<TaskCreateDto> updateDescription(@RequestBody TaskDescUpdateDto request) {
        service.updateDescription(request);
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
    public ResponseEntity<TaskCreateDto> updateTitle(@RequestBody TaskTitleUpdateDto request) {
        service.updateTitle(request);
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
    public ResponseEntity<TaskCreateDto> updateStatus(@Valid @RequestBody TaskStatusUpdateDto request) {
        service.updateStatus(request);
        return ResponseEntity.noContent().build();
    }
}
