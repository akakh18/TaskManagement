package com.example.userstories.core.tasks;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.example.userstories.core.tasks.Status.NOT_DONE;


public class Task {
    @Getter
    @Setter
    String status;

    @Setter
    @Getter
    String author;

    List<String> assignees;

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

}
