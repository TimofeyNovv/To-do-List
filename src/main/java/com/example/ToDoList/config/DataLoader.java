package com.example.ToDoList.config;

import com.example.ToDoList.model.entity.task.StatusTask;
import com.example.ToDoList.model.entity.task.TaskEntity;
import com.example.ToDoList.model.entity.user.Role;
import com.example.ToDoList.model.entity.user.UserEntity;
import com.example.ToDoList.repository.TaskRepository;
import com.example.ToDoList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createInitialData();
    }

    private void createInitialData() {
        // Создаем пользователей с зашифрованными паролями
        UserEntity user1 = createUserIfNotExists(
                "Timur Nov",
                "timn2020@inbox.ru",
                "Tima-n-a155",
                Role.USER
        );

        UserEntity user2 = createUserIfNotExists(
                "Alex Smith",
                "timn2023@gmail.com",
                "Tima-n-a155",
                Role.USER
        );

        UserEntity admin = createUserIfNotExists(
                "Admin User",
                "admin2020@gmail.com",
                "Tima-n-a155",
                Role.ADMIN
        );

        // Создаем задачи для пользователей
        createTasksForUser(user1, 3);
        createTasksForUser(user2, 3);
        createTasksForUser(admin, 2);

        log.info("Initial data loaded successfully");
    }

    private UserEntity createUserIfNotExists(String name, String email, String plainPassword, Role role) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    String encodedPassword = passwordEncoder.encode(plainPassword);

                    UserEntity user = UserEntity.builder()
                            .name(name)
                            .email(email)
                            .password(encodedPassword)
                            .role(role)
                            .build();

                    UserEntity savedUser = userRepository.save(user);
                    log.info("Created user: {} with role: {} and email: {}",
                            savedUser.getName(), savedUser.getRole(), savedUser.getEmail());
                    return savedUser;
                });
    }

    private void createTasksForUser(UserEntity user, int taskCount) {
        List<TaskEntity> existingTasks = taskRepository.findByOwner(user);

        if (!existingTasks.isEmpty()) {
            log.info("User {} already has {} tasks", user.getEmail(), existingTasks.size());
            return;
        }

        List<TaskEntity> tasks = List.of(
                // Задача 1
                TaskEntity.builder()
                        .title("Задача 1 для " + user.getName())
                        .description("Описание первой задачи пользователя " + user.getName())
                        .status(StatusTask.OPEN)
                        .owner(user)
                        .build(),

                // Задача 2
                TaskEntity.builder()
                        .title("Задача 2 для " + user.getName())
                        .description("Описание второй задачи пользователя " + user.getName())
                        .status(StatusTask.INPROGRESS)
                        .owner(user)
                        .build(),

                // Задача 3
                TaskEntity.builder()
                        .title("Задача 3 для " + user.getName())
                        .description("Описание третьей задачи пользователя " + user.getName())
                        .status(StatusTask.PLANNED)
                        .owner(user)
                        .build()
        );

        // Создаем только нужное количество задач
        List<TaskEntity> tasksToCreate = tasks.subList(0, Math.min(taskCount, tasks.size()));

        taskRepository.saveAll(tasksToCreate);
        log.info("Created {} tasks for user: {}", tasksToCreate.size(), user.getEmail());
    }
}