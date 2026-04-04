package com.gordeev.taskmanager.tasks.controller;

import com.gordeev.taskmanager.common.dto.ApiResponse;
import com.gordeev.taskmanager.common.dto.PageResponse;
import com.gordeev.taskmanager.common.security.CustomUserDetails;
import com.gordeev.taskmanager.tasks.dto.TaskCreateRequest;
import com.gordeev.taskmanager.tasks.dto.TaskResponse;
import com.gordeev.taskmanager.tasks.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(@AuthenticationPrincipal CustomUserDetails currentUser, @RequestBody @Valid TaskCreateRequest request) {
        TaskResponse result = taskService.createTask(currentUser, request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<TaskResponse>>> getTasks(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        PageResponse<TaskResponse> result = taskService.getTasks(currentUser, pageable);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> getTask(@AuthenticationPrincipal CustomUserDetails currentUser, @PathVariable Long id) {
        TaskResponse result = taskService.getTask(currentUser, id);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@AuthenticationPrincipal CustomUserDetails currentUser, @PathVariable Long id) {
        taskService.deleteTask(currentUser, id);

        return ResponseEntity.noContent().build();
    }

}
