package com.gordeev.taskmanager.tasks.service;

import com.gordeev.taskmanager.common.dto.PageResponse;
import com.gordeev.taskmanager.common.exception.ResourceDoesNotExistException;
import com.gordeev.taskmanager.common.security.CustomUserDetails;
import com.gordeev.taskmanager.tasks.dto.TaskCreateRequest;
import com.gordeev.taskmanager.tasks.dto.TaskResponse;
import com.gordeev.taskmanager.tasks.entity.Task;
import com.gordeev.taskmanager.tasks.mapper.TaskMapper;
import com.gordeev.taskmanager.tasks.repository.TaskRepository;
import com.gordeev.taskmanager.users.entity.User;
import com.gordeev.taskmanager.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {
    private static final String TASK_NOT_FOUND = "Задания с name/id '%s' не существует";
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponse createTask(CustomUserDetails currentUser, TaskCreateRequest request) {
        Task task = taskMapper.toTask(request);

        task.setUser(userRepository.getReferenceById(currentUser.getId()));

        Task saved = taskRepository.save(task);

        return taskMapper.toResponse(saved, currentUser.getUsername());
    }

    public PageResponse<TaskResponse> getTasks(CustomUserDetails currentUser, String name, Pageable pageable) {
        Page<Task> page;

        if (name != null && !name.isEmpty()) {
            page = taskRepository.findByName(name, pageable);
            if (page.isEmpty()) {
                throw new ResourceDoesNotExistException(String.format(TASK_NOT_FOUND, name));
            }
        } else {
            page = taskRepository.findByUserId(currentUser.getId(), pageable);
        }

        Page<TaskResponse> responsePage = page.map(task -> taskMapper.toResponse(task, currentUser.getUsername()));

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

    @Transactional
    public void deleteTask(CustomUserDetails currentUser, Long id) {
        Task task = taskRepository.findByIdAndUserId(id, currentUser.getId()).orElseThrow(
                () -> new ResourceDoesNotExistException(String.format(TASK_NOT_FOUND, id)));

        taskRepository.delete(task);
    }
}
