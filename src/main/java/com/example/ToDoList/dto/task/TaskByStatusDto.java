package com.example.ToDoList.dto.task;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskByStatusDto {
    @Pattern(regexp = "DONE|INPROGRESS|DEADLINE|PLANNED|OPEN", message = "Недопустимое значение статуса. Допустимые значения: DONE, INPROGRESS, DEADLINE, PLANNED, OPEN")
    private String status;
}
