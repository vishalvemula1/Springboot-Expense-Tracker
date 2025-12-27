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
    public UserResponse createUser(UserCreateRequest dto) {
        User user = userMapper.toEntity(dto);

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPasswordHash(encodedPassword);

        userRepository.save(user);

        return userMapper.toResponseDTO(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userMapper.updateEntityFromDTO(dto, user);

        if (dto.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(dto.getPassword());
            user.setPasswordHash(encodedPassword);
        }

        userRepository.save(user);

        return userMapper.toResponseDTO(user);

    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponse readUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return userMapper.toResponseDTO(user);
    }
}
