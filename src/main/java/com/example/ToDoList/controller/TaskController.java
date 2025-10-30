package com.example.ToDoList.controller;

import com.example.ToDoList.dto.ErrorResponse;
import com.example.ToDoList.dto.task.*;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.service.implService.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
                    @ApiResponse(responseCode = "200", description = "Задача найдена",content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content(schema = @Schema (implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "нету доступа, тк это не задача текущего пользователя", content = @Content(schema = @Schema (implementation = ErrorResponse.class)))
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
                    @ApiResponse(responseCode = "200", description = "Задача удалена",content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content(schema = @Schema (implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "нету доступа, тк это не задача текущего пользователя", content = @Content(schema = @Schema (implementation = ErrorResponse.class)))
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskEntity> delById(@AuthenticationPrincipal UserEntity user, @PathVariable Integer id) {
        service.deleteById(id,user);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Создать задачу",
            description = "Создаёт задачу"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Успешно создано",content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные", content = @Content(schema = @Schema (implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Владелец с таким id не найден", content = @Content(schema = @Schema (implementation = ErrorResponse.class)))
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
                    @ApiResponse(responseCode = "200", description = "Успешно обновлено",content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена", content = @Content(schema = @Schema (implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "нету доступа, тк это не задача текущего пользователя", content = @Content(schema = @Schema (implementation = ErrorResponse.class)))
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
                    @ApiResponse(responseCode = "200", description = "Успешно обновлено",content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена", content = @Content(schema = @Schema (implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "нету доступа, тк это не задача текущего пользователя", content = @Content(schema = @Schema (implementation = ErrorResponse.class)))
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
                    @ApiResponse(responseCode = "200", description = "Успешно обновлено",content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Задача с таким id не найдена", content = @Content(schema = @Schema (implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Невалидный статус задачи", content = @Content(schema = @Schema (implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "нету доступа, тк это не задача текущего пользователя", content = @Content(schema = @Schema (implementation = ErrorResponse.class)))
            }
    )
    @PatchMapping("update/status")
    public ResponseEntity<TaskCreateDto> updateStatus(@AuthenticationPrincipal UserEntity currentUser, @Valid @RequestBody TaskStatusUpdateDto request) {
        service.updateStatus(request, currentUser);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "получить список всех задач текущего пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "успешно", content = @Content(schema = @Schema (implementation = TaskSmallInfoDto[].class)))
            }
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllTaskForCurrentUser(@AuthenticationPrincipal UserEntity currentUser){
        return ResponseEntity.ok(service.findByOwner(currentUser));
    }
}
