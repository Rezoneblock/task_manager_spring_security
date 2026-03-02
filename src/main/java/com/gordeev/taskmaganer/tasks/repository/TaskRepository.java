package com.gordeev.taskmaganer.tasks.repository;

import com.gordeev.taskmaganer.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Boolean existsByName(String name);
}
