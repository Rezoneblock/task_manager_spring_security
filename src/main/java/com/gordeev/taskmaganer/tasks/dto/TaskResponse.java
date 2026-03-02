package com.gordeev.taskmaganer.tasks.dto;

public record TaskResponse(
        String name,
        String description,
        Boolean done
) {
}
