package com.gordeev.taskmanager.users.dto;

public record UserCreateRequest(
        String username,
        String password,
        String Email
) {
}
