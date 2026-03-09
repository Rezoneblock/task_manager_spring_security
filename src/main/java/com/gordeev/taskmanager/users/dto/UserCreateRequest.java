package com.gordeev.taskmanager.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @Size(min = 5, max = 20, message = "Имя пользователя должно быть от 5 до 20 символов")
        String username,

        @Size(min = 8, max = 20, message = "Пароль должен быть от 8 до 20 символов")
        String password,

        @Email
        String email
) {
}
