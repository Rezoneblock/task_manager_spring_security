package com.gordeev.taskmanager.users.dto;

public record UserResponse(
        Long id,
        String username,
        String email
) {
}
