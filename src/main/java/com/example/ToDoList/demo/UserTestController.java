package com.example.ToDoList.demo;

import com.example.ToDoList.dto.UserResponseDto;
import com.example.ToDoList.exception.UserNotFoundException;
import com.example.ToDoList.service.implService.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo/user")
@RequiredArgsConstructor
public class UserTestController {

    private final UserServiceImpl service;

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(service.findByEmail(email));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/email/{email}")
    public ResponseEntity<?> deleteByEmail(@PathVariable String email) {
        service.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
