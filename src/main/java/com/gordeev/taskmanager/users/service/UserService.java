package com.gordeev.taskmanager.users.service;

import com.gordeev.taskmanager.common.dto.PageResponse;
import com.gordeev.taskmanager.common.exception.ResourceAlreadyExistException;
import com.gordeev.taskmanager.common.exception.ResourceDoesNotExistException;
import com.gordeev.taskmanager.users.dto.UserCreateRequest;
import com.gordeev.taskmanager.users.dto.UserResponse;
import com.gordeev.taskmanager.users.entity.User;
import com.gordeev.taskmanager.users.mapper.UserMapper;
import com.gordeev.taskmanager.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private static final String USER_NOT_FOUND = "Такого пользователя не существует";
    private static final String USER_ALREADY_EXISTS = "Пользователь уже существует";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public PageResponse<UserResponse> getUsers(String username, Pageable pageable) {
        Page<User> page;

        if (username != null && !username.isEmpty()) {
            page = userRepository.findByUsername(username, pageable);
            if (page.isEmpty()) {
                throw new ResourceDoesNotExistException(USER_NOT_FOUND);
            }
        } else {
            page = userRepository.findAll(pageable);
        }

        Page<UserResponse> responsePage = page.map(userMapper::toResponse);

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
    public UserResponse createUser(UserCreateRequest request) {
        User user = userMapper.toUser(request);

        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistException(USER_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceDoesNotExistException(USER_NOT_FOUND);
        }

        userRepository.deleteById(id);
    }
}
