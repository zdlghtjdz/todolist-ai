package com.github.soh.todolist.todolist_ai.service;

import com.github.soh.todolist.todolist_ai.domain.Task;
import com.github.soh.todolist.todolist_ai.dto.TaskDTO;
import com.github.soh.todolist.todolist_ai.exception.ResourceNotFoundException;
import com.github.soh.todolist.todolist_ai.repository.TaskRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<TaskDTO> getAllTasks() {

        //return taskRepository.findAll();
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getDueDate())).collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        //return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 Task가 없습니다."));

        if (id < 0) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        //return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("해당 ID의 Task가 없습니다."));

        // 20250311 DTO 적용
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("해당 ID의 Task가 없습니다."));

        return new TaskDTO(task.getId(), task.getTitle(), task.getTitle(), task.getStatus(), task.getDueDate());

    }

    public TaskDTO getDeletedTaskById(Long id) {
        Task task = taskRepository.findByIdIncludingDeleted(id).orElseThrow(() -> new ResourceNotFoundException("해당 ID의 Task가 없습니다."));

        return new TaskDTO(task.getId(), task.getTitle(), task.getTitle(), task.getStatus(), task.getDueDate());
    }

    /**
     *  새로운 할 일 추가
     * @param task
     * @return
     */
    public TaskDTO createTask(Task task) {
        Task createdTask = taskRepository.save(task);
        return new TaskDTO(createdTask.getId(), createdTask.getTitle(), createdTask.getDescription(), createdTask.getStatus(), createdTask.getDueDate());
    }

    /**
     *  특정 할 일 수정
     * @param id
     * @param updatedTask
     * @return
     */
    public TaskDTO updateTask(Long id, Task updatedTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setDueDate(updatedTask.getDueDate());
            Task updatedAfterTask = taskRepository.save(task);
            return new TaskDTO(updatedAfterTask.getId(), updatedAfterTask.getTitle(), updatedAfterTask.getDescription(), updatedAfterTask.getStatus(), updatedAfterTask.getDueDate());
        }
        return null;
    }

    /**
     *  특정 할 일 삭제
     * @param id
     */
    public void deleteTask(Long id) {
        //taskRepository.deleteById(id);
        //2025-03-11
        //soft Delete 형태로 변경처리
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("해당 ID의 Task가 없습니다."));

        task.setDeleted(true);

        taskRepository.save(task);
    }
}
