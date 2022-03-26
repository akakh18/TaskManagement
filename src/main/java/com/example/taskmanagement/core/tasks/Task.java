package com.example.taskmanagement.core.tasks;

import com.example.taskmanagement.infra.controllers.task.dto.CreateTaskDTO;
import com.example.taskmanagement.infra.entity.TaskEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.taskmanagement.core.tasks.Status.NOT_DONE;


public class Task {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String status;

    @Setter
    @Getter
    private String author;

    @Setter
    @Getter
    private Date due;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String description;

    private final List<String> assignees;

    public Task() {
        this.status = NOT_DONE;
        assignees = new ArrayList<>();
    }

    public void setAssignee(String assignee) {
        assignees.add(assignee);
    }

    public List<String> getAssignees() {
        return assignees;
    }

    private static Task getTaskFrom(Long id, String status, String author,
                                    Date due, String title, String description) {
        Task result = new Task();
        result.setId(id);
        result.setStatus(status);
        result.setAuthor(author);
        result.setDue(due);
        result.setTitle(title);
        result.setDescription(description);
        return result;
    }

    public static Task from(CreateTaskDTO task) {
        Task result = getTaskFrom(null, task.getStatus(),
                task.getAuthor(), task.getDue(), task.getTitle(), task.getDescription());
        List.of(task.getAssignees()).forEach(result::setAssignee);
        return result;
    }

    public static Task from(TaskEntity task) {
        Task result = getTaskFrom(task.getId(), task.getStatus(), task.getAuthor()
                , task.getDue(), task.getTitle(), task.getDescription());
        task.getAssignees().forEach(assignee -> result.setAssignee(assignee.getUsername()));
        result.setDue(task.getDue());
        return result;
    }
}
