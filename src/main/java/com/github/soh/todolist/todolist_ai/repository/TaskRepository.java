package com.github.soh.todolist.todolist_ai.repository;

import com.github.soh.todolist.todolist_ai.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
