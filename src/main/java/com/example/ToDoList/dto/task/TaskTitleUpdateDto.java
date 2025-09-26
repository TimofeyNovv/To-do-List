package com.example.ToDoList.dto.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskTitleUpdateDto {
    private Long taskId;
    private String newTitle;
}
