package com.gordeev.taskmanager.users.dto;

public record AuthRequest(
        String username,
        String password
) {
}
