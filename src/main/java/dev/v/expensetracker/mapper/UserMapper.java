package dev.v.expensetracker.mapper;

import dev.v.expensetracker.dto.userDTO.UserCreateRequest;
import dev.v.expensetracker.dto.userDTO.UserResponse;
import dev.v.expensetracker.dto.userDTO.UserUpdateRequest;
import dev.v.expensetracker.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public UserResponse toResponseDTO(User user) {
        if (user == null) return null;

        UserResponse dto = new UserResponse();

        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        return dto;
    }

    public User toEntity(UserCreateRequest dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        return user;
    }

    public void updateEntityFromDTO(UserUpdateRequest dto, User user) {
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
    }
}
