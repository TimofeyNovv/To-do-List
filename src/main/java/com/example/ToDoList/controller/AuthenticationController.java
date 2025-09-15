package com.example.ToDoList.controller;

import com.example.ToDoList.dto.auth.AuthenticationRequest;
import com.example.ToDoList.dto.auth.AuthenticationResponse;
import com.example.ToDoList.dto.auth.RegisterRequest;
import com.example.ToDoList.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            summary = "регистрация нового пользователя",
            description = "Создаёт нового пользователя  и возвращает jwt токены "
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(
            summary = "аунтетификация пользователя",
            description = "Возвразщает jwt токены, для достапа к api "
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
