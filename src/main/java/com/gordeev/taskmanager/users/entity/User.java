package com.gordeev.taskmanager.users.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @Size(min = 8, max = 20, message = "Имя пользователя должно быть от 5 до 20 символов")
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    @Size(min = 8, max = 20, message = "Пароль должен быть от 8 до 20 символов")
    private String password;

    @Email
    @Column(nullable = false)
    private String email;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
