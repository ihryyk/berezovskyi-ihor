package com.epam.hw_4.controller.mapper;

import com.epam.hw_4.controller.dto.UserDTO;
import com.epam.hw_4.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User mapToEntity(UserDTO userDTO);

  UserDTO mapToDto(User user);
}
