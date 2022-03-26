package com.example.taskmanagement.core.services;

import com.example.taskmanagement.core.repositories.TaskRepository;
import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.core.users.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public void createNewTask(Task task) {
        List<User> users = task.getAssignees().stream()
                .map(userService::getUserByUsername).collect(Collectors.toList());

        for (User user : users) {
            user.setTasks(List.of(task));
        }

        taskRepository.createNewTask(task);
    }

    public List<Task> filterBy(List<String> assignees, String status, boolean overdue) {
        List<Task> result = new ArrayList<>();

        assignees.forEach(assignee -> result.addAll(
                        userService.getTasksForUser(assignee)
                )
        );

        return result.stream()
                .filter(task -> task.getStatus().equals(status) && isOverDue(task.getDue()) == overdue)
                .collect(Collectors.toList());
    }

    private boolean isOverDue(Date due) {
        return due.compareTo(new Date()) <= 0;
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void changeStatus(String title, String status) {
        taskRepository.changeStatus(title, status);
    }

    public String getDescription(String title) {
        return taskRepository.getDescription(title);
    }

    public Task getById(Long id) {
        return taskRepository.getByTaskId(id);
    }
}
