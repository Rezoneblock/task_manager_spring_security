package com.gordeev.taskmanager.tasks.repository;

import com.gordeev.taskmanager.tasks.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Boolean existsByName(String name);

    Page<Task> findByName(String name, Pageable pageable);
}
