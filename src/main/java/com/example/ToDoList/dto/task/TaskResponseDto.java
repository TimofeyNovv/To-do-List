package com.example.ToDoList.dto.task;

import com.example.ToDoList.dto.user.UserSmallInfoDto;
import com.example.ToDoList.model.entity.task.StatusTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {

    private String title;
    private String description;
    private StatusTask status;
    private UserSmallInfoDto owner;

}
