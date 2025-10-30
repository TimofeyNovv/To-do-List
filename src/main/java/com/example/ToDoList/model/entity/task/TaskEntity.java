package com.example.ToDoList.model.entity.task;

import com.example.ToDoList.model.entity.BaseEntity;
import com.example.ToDoList.model.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_table")
public class TaskEntity extends BaseEntity {

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatusTask status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity owner;

}
