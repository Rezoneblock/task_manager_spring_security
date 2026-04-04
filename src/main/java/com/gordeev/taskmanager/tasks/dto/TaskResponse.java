package com.gordeev.taskmanager.tasks.dto;

public record TaskResponse(
        String username,
        String name,
        String description,
        Boolean done
) {
}
