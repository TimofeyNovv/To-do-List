package com.example.ToDoList.controller;

import com.example.ToDoList.dto.ErrorResponse;
import com.example.ToDoList.dto.auth.AuthenticationRequest;
import com.example.ToDoList.dto.auth.AuthenticationResponse;
import com.example.ToDoList.dto.auth.RegisterRequest;
import com.example.ToDoList.service.security.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API для аутентификации и управления пользователями")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            summary = "регистрация нового пользователя",
            description = "Создаёт нового пользователя  и возвращает jwt токены",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная регистрация"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные", content = @Content(schema = @Schema (implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Пользователь уже существует", content = @Content(schema = @Schema (implementation = ErrorResponse.class)))
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
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(
            summary = "аунтетификация пользователя",
            description = "Возвразщает jwt токены, для достапа к api",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная аунтентификация"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Неверный пароль",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Нет пользователя с таким email",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Учетные данные пользователя"
            )
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
