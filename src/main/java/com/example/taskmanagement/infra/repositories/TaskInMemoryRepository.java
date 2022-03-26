package com.example.taskmanagement.infra.repositories;

import com.example.taskmanagement.core.repositories.TaskRepository;
import com.example.taskmanagement.core.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskInMemoryRepository implements TaskRepository {
    private final List<Task> tasks;


    public TaskInMemoryRepository() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void createNewTask(Task taskRequest) {
        tasks.add(taskRequest);
    }

    @Override
    public Task getByTaskId(Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }


    @Override
    public void changeStatus(String title, String status) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                task.setStatus(status);
            }
        }
    }

    @Override
    public String getDescription(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                return task.getDescription();
            }
        }

        return null;
    }
}
