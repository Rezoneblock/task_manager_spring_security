package com.gordeev.taskmaganer.tasks.dto;

public record TaskCreateRequest(
        String name,
        String description
) {
}
