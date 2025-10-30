package com.example.ToDoList.dto.task;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для создания задачи")
public class TaskCreateDto {

    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;
    private String description;

    @NotNull(message = "Статус не может отсутствовать")
    @Pattern(regexp = "DONE|INPROGRESS|DEADLINE|PLANNED|OPEN", message = "Недопустимое значение статуса. Допустимые значения: DONE, INPROGRESS, DEADLINE, PLANNED, OPEN")
    private String status;
}
