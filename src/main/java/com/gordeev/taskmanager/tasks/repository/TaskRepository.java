package com.gordeev.taskmanager.tasks.repository;

import com.gordeev.taskmanager.tasks.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByName(String name);

    Page<Task> findByName(String name, Pageable pageable);

    Page<Task> findByUserId(UUID userId, Pageable pageable);
}
