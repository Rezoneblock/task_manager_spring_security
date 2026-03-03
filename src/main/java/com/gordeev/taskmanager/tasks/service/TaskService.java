package com.gordeev.taskmanager.tasks.service;

import com.gordeev.taskmanager.common.dto.PageResponse;
import com.gordeev.taskmanager.common.exception.ResourceDoesNotExistException;
import com.gordeev.taskmanager.tasks.dto.TaskCreateRequest;
import com.gordeev.taskmanager.tasks.dto.TaskResponse;
import com.gordeev.taskmanager.tasks.entity.Task;
import com.gordeev.taskmanager.tasks.mapper.TaskMapper;
import com.gordeev.taskmanager.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {
    private static final String TASK_NOT_FOUND = "Задания с именем '%s' не существует";
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = taskMapper.toTask(request);

        Task saved = taskRepository.save(task);

        return taskMapper.toResponse(saved);
    }

    public PageResponse<TaskResponse> findTasks(String name, Pageable pageable) {
        Page<Task> page;

        if (name != null && !name.isEmpty()) {
            page = taskRepository.findByName(name, pageable);
            if (page.isEmpty()) {
                throw new ResourceDoesNotExistException(String.format(TASK_NOT_FOUND, name));
            }
        } else {
            page = taskRepository.findAll(pageable);
        }

        Page<TaskResponse> responsePage = page.map(taskMapper::toResponse);

        return new PageResponse<>(
                responsePage.getContent(),
                new PageResponse.Metadata(
                        responsePage.getSize(),
                        responsePage.getTotalElements(),
                        responsePage.getTotalPages(),
                        responsePage.getNumber()
                )
        );
    }
}
