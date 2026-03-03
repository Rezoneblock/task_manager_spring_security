package com.gordeev.taskmanager.tasks.mapper;

import com.gordeev.taskmanager.tasks.dto.TaskCreateRequest;
import com.gordeev.taskmanager.tasks.dto.TaskResponse;
import com.gordeev.taskmanager.tasks.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponse toResponse(Task task);

    @Mapping(target = "done", constant = "false")
    Task toTask(TaskCreateRequest request);
}
