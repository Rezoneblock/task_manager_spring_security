package com.gordeev.taskmanager.tasks.dto;

public record TaskResponse(
        Long id,
        String username,
        String name,
        String description,
        Boolean done
) {
}
