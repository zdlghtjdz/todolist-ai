package com.github.soh.todolist.todolist_ai.service;

import com.github.soh.todolist.todolist_ai.domain.Task;
import com.github.soh.todolist.todolist_ai.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     *
     * @param taskRepository
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     *  모든 할일 목록 조회
     * @return
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     *  새로운 할 일 추가
     * @param task
     * @return
     */
    @Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     *  특정 할 일 수정
     * @param id
     * @param updatedTask
     * @return
     */
    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(task);
        }
        return null;
    }

    /**
     *  특정 할 일 삭제
     * @param id
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
