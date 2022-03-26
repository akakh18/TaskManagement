package com.example.taskmanagement.infra.daos;

import com.example.taskmanagement.infra.entity.TaskEntity;
import com.example.taskmanagement.infra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepositoryDAO extends JpaRepository<TaskEntity, Long> {
    TaskEntity findByTitle(String title);

    List<TaskEntity> findAllByAssigneesContainingOrAuthor(UserEntity assignee, String author);
}
