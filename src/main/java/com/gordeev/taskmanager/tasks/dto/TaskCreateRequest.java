package com.gordeev.taskmanager.tasks.dto;

public record TaskCreateRequest(
        String name,
        String description
) {
}
