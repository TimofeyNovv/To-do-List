package com.example.ToDoList.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для обновления описания задачи")
public class TaskDescUpdateDto {
    private Long taskId;

    private String newDescription;
}
