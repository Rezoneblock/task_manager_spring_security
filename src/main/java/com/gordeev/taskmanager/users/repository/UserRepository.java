package com.gordeev.taskmanager.users.repository;

import com.gordeev.taskmanager.users.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Page<User> findByUsername(String username, Pageable pageable);

    Optional<User> findByUsername(String username);
}
