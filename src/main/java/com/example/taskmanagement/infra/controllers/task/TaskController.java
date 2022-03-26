package com.example.taskmanagement.infra.controllers.task;

import com.example.taskmanagement.core.services.TaskService;
import com.example.taskmanagement.core.services.UserService;
import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.infra.controllers.task.dto.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping
    public void createTask(@RequestBody CreateTaskDTO task) {
        taskService.createNewTask(Task.from(task));
    }

    @PostMapping("/filter")
    public FilterTasksResponseDTO filterTasks(@RequestBody FilterTasksRequestDTO filterTasksRequestDTO) {
        List<Task> result = taskService.filterBy(filterTasksRequestDTO.getAssignees(),
                filterTasksRequestDTO.getStatus(), filterTasksRequestDTO.isOverdue());
        return new FilterTasksResponseDTO(result);
    }

    @GetMapping
    public FilterTasksResponseDTO getAllTasks() {
        return new FilterTasksResponseDTO(taskService.getAllTasks());
    }

    @PostMapping("/status/change")
    public void changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        taskService.changeStatus(changeStatusDTO.getTitle(), changeStatusDTO.getStatus());
    }

    @PostMapping("/description")
    public DescriptionResponseDTO getDescription(@RequestBody GetDescriptionDTO getDescriptionDTO) {
        return new DescriptionResponseDTO(taskService.getById(getDescriptionDTO.getTitle()).getDescription());
    }

    @GetMapping("/profile")
    public FilterTasksResponseDTO getMyTasks(Principal principal) {
        return new FilterTasksResponseDTO(taskService.getMyTasks(principal.getName()));
    }

}
