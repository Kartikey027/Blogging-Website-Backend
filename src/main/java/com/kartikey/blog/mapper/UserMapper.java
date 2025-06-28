package com.kartikey.blog.mapper;

import com.kartikey.blog.dto.user.UserDTO;
import com.kartikey.blog.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
}
