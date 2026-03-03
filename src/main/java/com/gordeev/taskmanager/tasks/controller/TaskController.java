package com.gordeev.taskmanager.tasks.controller;

import com.gordeev.taskmanager.common.dto.ApiResponse;
import com.gordeev.taskmanager.common.dto.PageResponse;
import com.gordeev.taskmanager.tasks.dto.TaskCreateRequest;
import com.gordeev.taskmanager.tasks.dto.TaskResponse;
import com.gordeev.taskmanager.tasks.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(@RequestBody @Valid TaskCreateRequest request) {
        TaskResponse result = taskService.createTask(request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<PageResponse<TaskResponse>> getTasks(
            @RequestParam String name,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {

    }

}
