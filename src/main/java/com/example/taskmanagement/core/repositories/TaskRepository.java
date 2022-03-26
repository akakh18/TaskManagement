package com.example.taskmanagement.core.repositories;

import com.example.taskmanagement.core.tasks.Task;

import java.util.List;

public interface TaskRepository {
    void createNewTask(Task task);

    Task getByTaskId(Long id);

    List<Task> getAllTasks();

    void changeStatus(String title, String status);

    String getDescription(String title);
}
