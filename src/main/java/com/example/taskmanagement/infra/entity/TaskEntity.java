package com.example.taskmanagement.infra.entity;

import com.example.taskmanagement.core.tasks.Task;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "Tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaskIdSeq")
    private Long id;

    private String author;

    @ManyToMany
    private List<UserEntity> assignees;

    private String status;

    private Date due;

    private String title;

    private String Description;

    public static TaskEntity from(Task task) {
        TaskEntity result = new TaskEntity();
        result.setAuthor(task.getAuthor());
        result.setStatus(task.getStatus());
        result.setDue(task.getDue());
        result.setAssignees(task.getAssignees().stream().map(assignee -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(assignee);
            return userEntity;
        }).collect(Collectors.toList()));
        result.setTitle(task.getTitle());
        result.setDescription(task.getDescription());
        return result;
    }
}
