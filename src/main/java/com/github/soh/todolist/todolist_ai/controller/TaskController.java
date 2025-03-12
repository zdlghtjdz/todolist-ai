package com.github.soh.todolist.todolist_ai.controller;

import com.github.soh.todolist.todolist_ai.domain.Task;
import com.github.soh.todolist.todolist_ai.dto.TaskDTO;
import com.github.soh.todolist.todolist_ai.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {return taskService.getTaskById(id); }

    @GetMapping("/deleted/{id}")
    public TaskDTO getDeletedTaskById(@PathVariable Long id) {
        return taskService.getDeletedTaskById(id);
    }
    @PostMapping
    public TaskDTO createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
