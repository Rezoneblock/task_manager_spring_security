package com.gordeev.taskmanager.tasks.dto;

public record TaskResponse(
        String name,
        String description,
        Boolean done
) {
}
