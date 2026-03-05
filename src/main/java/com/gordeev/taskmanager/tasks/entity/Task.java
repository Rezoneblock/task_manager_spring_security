package com.gordeev.taskmanager.tasks.entity;

import com.gordeev.taskmanager.users.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(
            name = "task_seq",
            sequenceName = "tasks_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100, message = "Имя задачи должно быть минимум 3 символа и максимум 100 символов")
    private String name;

    @Column(length = 500)
    @Size(max = 500, message = "Описани должно быть максимум 500 символов")
    private String description;

    private Boolean done = false;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
