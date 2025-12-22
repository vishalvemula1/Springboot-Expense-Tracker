package dev.v.expensetracker.controller;

import dev.v.expensetracker.dto.userDTO.UserCreateRequest;
import dev.v.expensetracker.dto.userDTO.UserResponse;
import dev.v.expensetracker.dto.userDTO.UserUpdateRequest;
import dev.v.expensetracker.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public UserResponse createUser(@Valid @RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable Long userId,
                                   @Valid @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return userService.readUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
