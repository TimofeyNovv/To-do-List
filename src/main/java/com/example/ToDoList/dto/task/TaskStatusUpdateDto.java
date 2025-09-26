package com.example.ToDoList.dto.task;

import com.example.ToDoList.model.entity.task.StatusTask;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskStatusUpdateDto {
    private Long taskId;
    private StatusTask status;
}
