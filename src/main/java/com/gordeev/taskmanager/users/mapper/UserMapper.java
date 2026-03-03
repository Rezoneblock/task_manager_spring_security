package com.gordeev.taskmanager.users.mapper;

import com.gordeev.taskmanager.users.dto.UserCreateRequest;
import com.gordeev.taskmanager.users.dto.UserResponse;
import com.gordeev.taskmanager.users.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toResponse(User user);
}
