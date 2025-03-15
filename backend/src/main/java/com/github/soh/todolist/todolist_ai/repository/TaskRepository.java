package com.github.soh.todolist.todolist_ai.repository;

import com.github.soh.todolist.todolist_ai.domain.Task;
import com.github.soh.todolist.todolist_ai.dto.TaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM Task t WHERE t.ID = :id", nativeQuery = true)
    Optional<Task> findByIdIncludingDeleted(@Param("id") Long id);

    List<TaskProjection> findBy();

}
