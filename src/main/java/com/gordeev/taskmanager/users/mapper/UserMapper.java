package com.gordeev.taskmanager.users.mapper;

import com.gordeev.taskmanager.users.dto.UserCreateRequest;
import com.gordeev.taskmanager.users.dto.UserResponse;
import com.gordeev.taskmanager.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "password", ignore = true)
    User toUser(UserCreateRequest request);

    UserResponse toResponse(User user);
}
