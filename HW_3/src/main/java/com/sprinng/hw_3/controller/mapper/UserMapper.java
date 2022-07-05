package com.sprinng.hw_3.controller.mapper;

import com.sprinng.hw_3.controller.dto.UserDTO;
import com.sprinng.hw_3.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapToEntity(UserDTO userDTO);

    UserDTO mapToDto(User user);
}
