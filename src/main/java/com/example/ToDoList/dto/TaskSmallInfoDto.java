package com.example.ToDoList.dto;

import com.example.ToDoList.model.entity.task.StatusTask;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskSmallInfoDto {

    private String title;
    private String description;
    private StatusTask status;

}
