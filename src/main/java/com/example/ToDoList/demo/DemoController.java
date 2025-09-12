package com.example.ToDoList.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    @Operation(
            summary = "текст work!",
            description = "созданно для проверки работы аунтентифекации"
    )
    @GetMapping
    public ResponseEntity<String> seyHello(){
        return ResponseEntity.ok("work!");
    }
}
