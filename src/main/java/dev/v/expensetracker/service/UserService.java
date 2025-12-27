package dev.v.expensetracker.service;

import dev.v.expensetracker.dto.userDTO.UserCreateRequest;
import dev.v.expensetracker.dto.userDTO.UserResponse;
import dev.v.expensetracker.dto.userDTO.UserUpdateRequest;
import dev.v.expensetracker.entity.User;
import dev.v.expensetracker.exception.ResourceNotFoundException;
import dev.v.expensetracker.mapper.UserMapper;
import dev.v.expensetracker.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;

    }


    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(User.class, "id", userId));

        userMapper.updateEntityFromDTO(dto, user);

        if (dto.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(dto.getPassword());
            user.setPasswordHash(encodedPassword);
        }

        userRepository.save(user);

        return userMapper.toResponseDTO(user);

    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public UserResponse readUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(User.class, "id", userId));

        return userMapper.toResponseDTO(user);
    }
}
