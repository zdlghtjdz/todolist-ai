package com.github.soh.todolist.todolist_ai.dto;

import com.github.soh.todolist.todolist_ai.domain.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {
   private Long id;
   private String title;
   private String description;
   private TaskStatus status;
   private LocalDateTime dueDate;

   public TaskDTO(Long id, String title, String description, TaskStatus status, LocalDateTime dueDate) {
       this.id = id;
       this.title = title;
       this.description = description;
       this.status = status;
       this.dueDate = dueDate;
   }
}
