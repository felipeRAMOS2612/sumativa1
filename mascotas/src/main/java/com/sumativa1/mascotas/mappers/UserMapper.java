package com.sumativa1.mascotas.mappers;

import org.springframework.stereotype.Component;

import com.sumativa1.mascotas.common.mappers.EntityMapper;
import com.sumativa1.mascotas.dtos.UserRequest;
import com.sumativa1.mascotas.entities.User;

@Component
public class UserMapper extends EntityMapper<UserRequest, User> {

    @Override
    public User toEntity(UserRequest dto) {
        return new User(null, dto.getName(), dto.getRoleId());
    }

    @Override
    public UserRequest toDto(User entity) {
        UserRequest dto = new UserRequest();
        dto.setName(entity.getName());
        dto.setRoleId(entity.getRoleId());
        return dto;
    }
}

