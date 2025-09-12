package com.example.ToDoList.demo;

import com.example.ToDoList.dto.TaskResponseDto;
import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.service.implService.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("demo/task")
@RequiredArgsConstructor
public class TaskTestController {

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
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.findById(id));
        }
        catch (TaskNotFoundException  e) {
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
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskEntity> delById(@PathVariable Integer id){
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (TaskNotFoundException  e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Создать задачу",
            description = "Создаёт задачу"
    )
    @PostMapping("/create")
    public ResponseEntity<TaskEntity> create(@RequestBody TaskEntity entity){
        service.create(entity);
        return ResponseEntity.noContent().build();
    }
}
