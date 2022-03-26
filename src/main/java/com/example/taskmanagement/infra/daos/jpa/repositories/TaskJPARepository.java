package com.example.taskmanagement.infra.daos.jpa.repositories;

import com.example.taskmanagement.core.repositories.TaskRepository;
import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.infra.daos.TaskRepositoryDAO;
import com.example.taskmanagement.infra.entity.TaskEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("main")
public class TaskJPARepository implements TaskRepository {
    private final TaskRepositoryDAO taskRepositoryDAO;

    public TaskJPARepository(TaskRepositoryDAO taskRepositoryDAO) {
        this.taskRepositoryDAO = taskRepositoryDAO;
    }

    @Override
    public void createNewTask(Task task) {
        Task.from(taskRepositoryDAO.save(TaskEntity.from(task)));
    }

    @Override
    public Task getByTaskId(Long id) {
        return Task.from(taskRepositoryDAO.findById(id).orElseThrow());
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryDAO.findAll().stream().map(Task::from).collect(Collectors.toList());
    }

    @Override
    public void changeStatus(String title, String status) {
        TaskEntity task = taskRepositoryDAO.findByTitle(title);
        task.setStatus(status);
        taskRepositoryDAO.save(task);
    }

    @Override
    public String getDescription(String title) {
        return taskRepositoryDAO.findByTitle(title).getDescription();
    }

}
