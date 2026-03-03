package com.gordeev.taskmanager.users.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
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
}
