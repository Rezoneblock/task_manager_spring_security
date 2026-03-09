package com.gordeev.taskmanager.tasks.dto;

import jakarta.validation.constraints.Size;

public record TaskCreateRequest(
        @Size(min = 3, max = 100, message = "Имя задачи должно быть минимум 3 символа и максимум 100 символов")
        String name,

        @Size(max = 500, message = "Описани должно быть максимум 500 символов")
        String description
) {
}
