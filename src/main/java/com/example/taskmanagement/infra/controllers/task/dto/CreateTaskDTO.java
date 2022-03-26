package com.example.taskmanagement.infra.controllers.task.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateTaskDTO {
    private String author;
    private String[] assignees;
    private String status;
    private Date due;
    private String title;
    private String description;
}
