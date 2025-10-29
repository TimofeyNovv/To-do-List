package com.example.ToDoList.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO для обновления статуса задачи")
public class TaskStatusUpdateDto {
    private Long taskId;
    @Pattern(regexp = "DONE|INPROGRESS|DEADLINE|PLANNED|OPEN", message = "Недопустимое значение статуса. Допустимые значения: DONE, INPROGRESS, DEADLINE, PLANNED, OPEN")
    private String status;
}
