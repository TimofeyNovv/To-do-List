package com.example.ToDoList.unit.services;

import com.example.ToDoList.dto.user.UserResponseDto;
import com.example.ToDoList.exception.UserNotFoundException;
import com.example.ToDoList.model.entity.task.StatusTask;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.Role;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.repository.UserRepository;
import com.example.ToDoList.service.implService.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock//объект, который имитирует поведение реального объекта,
    // но не выполняет реальную логику. нужен, чтобы изолировать т
    // естируемый класс от его зависимостей.
    private UserRepository userRepository;

    @InjectMocks//создает реальный объект и "внедряет" в него все моки, помеченные @Mock
    private UserServiceImpl userService;

    // Теперь userService работает с userRepository-моком, а не с реальной БД

    private UserEntity testUser;
    private UserEntity testUserWithTasks;

    @BeforeEach
//метод выполняется ПЕРЕД КАЖДЫМ тестом
    void setUp() {
        testUser = UserEntity.builder()
                .name("TimofeyNovv")
                .email("timn2020@inbox.ru")
                .password("Pass123+-")
                .role(Role.USER)
                .tasks(new ArrayList<>())
                .build();

        testUserWithTasks = UserEntity.builder()
                .name("TimofeyNovv2")
                .email("timn2023@gmail.com")
                .role(Role.ADMIN)
                .tasks(List.of(
                        TaskEntity.builder().title("Task 1").description("Desc 1").status(StatusTask.DONE).build(),
                        TaskEntity.builder().title("Task 2").description("Desc 2").status(StatusTask.DEADLINE).build()
                ))
                .build();
    }

    //___________________________________findByIdTests_______________________________________________
    @Test
    void findById_WhenUserExists_ReturnsUserResponseDto() {

        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));

        UserResponseDto result = userService.findById(1);

        assertEquals("TimofeyNovv", result.getName());
        assertEquals("timn2020@inbox.ru", result.getEmail());
        assertEquals(Role.USER, result.getRole());
    }

    @Test
    void findById_WhenUserHasTasks_ReturnsUserWithTasks() {
        when(userRepository.findById(2)).thenReturn(Optional.of(testUserWithTasks));

        UserResponseDto result = userService.findById(2);

        assertEquals("TimofeyNovv2", result.getName());
        assertEquals("timn2023@gmail.com", result.getEmail());
        assertEquals(Role.ADMIN, result.getRole());

        assertEquals("Task 1", result.getTasks().get(0).getTitle());
        assertEquals("Desc 1", result.getTasks().get(0).getDescription());
        assertEquals(StatusTask.DONE, result.getTasks().get(0).getStatus());

        assertEquals("Task 2", result.getTasks().get(1).getTitle());
        assertEquals("Desc 2", result.getTasks().get(1).getDescription());
        assertEquals(StatusTask.DEADLINE, result.getTasks().get(1).getStatus());
    }

    @Test
    void findById_WhenUserNotFound_ThrowsUserNotFoundException() {

        when(userRepository.findById(999)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class, () -> userService.findById(999)
        );

        assertEquals("User with id - " + 999 + " not found", exception.getMessage());
    }
}


