package com.example.ToDoList.demo;

import com.example.ToDoList.dto.TaskResponseDto;
import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.service.implService.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("demo/task")
@RequiredArgsConstructor
public class TaskTestController {
    private final TaskServiceImpl service;

    @GetMapping("/id/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.findById(id));
        }
        catch (TaskNotFoundException  e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<TaskEntity> delById(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("create")
    public ResponseEntity<TaskEntity> create(@RequestBody TaskEntity entity){
        service.create(entity);
        return ResponseEntity.noContent().build();
    }
}
