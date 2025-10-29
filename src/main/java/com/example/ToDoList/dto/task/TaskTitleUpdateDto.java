package com.example.ToDoList.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO для обновления заголовка задачи")
public class TaskTitleUpdateDto {
    private Long taskId;
    private String newTitle;
}
