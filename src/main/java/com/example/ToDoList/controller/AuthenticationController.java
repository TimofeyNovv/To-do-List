package com.example.ToDoList.controller;

import com.example.ToDoList.dto.auth.AuthenticationRequest;
import com.example.ToDoList.dto.auth.AuthenticationResponse;
import com.example.ToDoList.dto.auth.RegisterRequest;
import com.example.ToDoList.exception.UserAlreadyExistsException;
import com.example.ToDoList.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            description = "Создаёт нового пользователя  и возвращает jwt токены",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная регистрация"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "409", description = "Пользователь уже существует")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для регистрации нового пользователя",
                    required = true
            )
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
            } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Operation(
            summary = "аунтетификация пользователя",
            description = "Возвразщает jwt токены, для достапа к api"
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
