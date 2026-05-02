package com.example.bookapi.mapper;

import com.example.bookapi.dto.UserDTO;
import com.example.bookapi.entity.Role;
import com.example.bookapi.entity.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .build();
    }

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .build(); 
    }
}