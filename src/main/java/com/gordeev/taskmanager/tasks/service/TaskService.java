package com.gordeev.taskmanager.tasks.service;

import com.gordeev.taskmanager.tasks.dto.TaskCreateRequest;
import com.gordeev.taskmanager.tasks.dto.TaskResponse;
import com.gordeev.taskmanager.tasks.entity.Task;
import com.gordeev.taskmanager.tasks.mapper.TaskMapper;
import com.gordeev.taskmanager.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = taskMapper.toTask(request);

        Task saved = taskRepository.save(task);

        return taskMapper.toResponse(saved);
    }
}
