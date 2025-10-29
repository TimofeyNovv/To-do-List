package com.example.ToDoList.dto.task;

import com.example.ToDoList.model.entity.task.StatusTask;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO для краткой информацией о задаче")
public class TaskSmallInfoDto {

    private String title;
    private String description;
    private StatusTask status;

}
