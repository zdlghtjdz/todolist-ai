package com.github.soh.todolist.todolist_ai.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity // JPA 엔티티 선언
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가하는 기본키
    private Long id; // ID

    private String title; // 제목
    private String description; // 상세 내용

    @Enumerated(EnumType.STRING) // ENUM 타입을 String으로 저장함.
    private TaskStatus status; // 상태

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    private LocalDateTime dueDate; // 마감일
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시간

}
