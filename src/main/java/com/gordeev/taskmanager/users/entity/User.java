package com.gordeev.taskmanager.users.entity;

import com.gordeev.taskmanager.tasks.entity.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 8, max = 20, message = "Имя пользователя должно быть от 5 до 20 символов")
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    @Size(min = 8, max = 20, message = "Пароль должен быть от 8 до 20 символов")
    private String password;

    @Email
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setUser(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setUser(null);
    }
}
