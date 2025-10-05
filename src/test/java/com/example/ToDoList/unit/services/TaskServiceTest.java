package com.example.ToDoList.unit.services;

import com.example.ToDoList.dto.task.TaskResponseDto;
import com.example.ToDoList.exception.TaskNotFoundException;
import com.example.ToDoList.model.entity.task.StatusTask;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.Role;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.repository.TaskRepository;
import com.example.ToDoList.service.implService.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private TaskEntity testTask;

    private UserEntity user;

    @BeforeEach
    void setUp() {
         user = UserEntity.builder()
                .name("TimNovv")
                .email("timn2020@inbox.ru")
                .password("Tima-n-a155")
                .role(Role.USER)
                .build();

        testTask = TaskEntity.builder()
                .title("Titl1")
                .description("Desc1")
                .status(StatusTask.DONE)
                .owner(user)
                .build();

        user.setTasks(List.of(testTask));
    }

    //___________________________________findByIdTests________________________________
    @Test
    void findById_WhenTaskExists_ReturnsTaskResponseDto(){
        when(repository.findById(1)).thenReturn(Optional.of(testTask));

        TaskResponseDto result = taskService.findById(1);

        assertEquals("Titl1", result.getTitle());
        assertEquals("Desc1", result.getDescription());
        assertEquals(StatusTask.DONE, result.getStatus());

        assertEquals("TimNovv", result.getOwner().getName());
        assertEquals("timn2020@inbox.ru", result.getOwner().getEmail());
        assertEquals(Role.USER, result.getOwner().getRole());
    }

    @Test
    void findById_WhenTaskNotFound_ThrowsTaskNotFoundException(){

        when(repository.findById(999)).thenReturn(Optional.empty());

        TaskNotFoundException exception = assertThrows(
                TaskNotFoundException.class, () -> taskService.findById(999)
        );

        assertEquals("Task with id - " + 999 + " not found", exception.getMessage());
    }


}
