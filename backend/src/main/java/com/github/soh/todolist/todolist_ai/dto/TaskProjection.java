package com.github.soh.todolist.todolist_ai.dto;

import com.github.soh.todolist.todolist_ai.domain.TaskStatus;
import java.time.LocalDateTime;

public interface TaskProjection {

    Long getId();
    String getTitle();
    String getDescription();
    TaskStatus getStatus();
    LocalDateTime getDueDate();
    
}
