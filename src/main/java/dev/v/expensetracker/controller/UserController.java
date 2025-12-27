package dev.v.expensetracker.controller;

import dev.v.expensetracker.dto.userDTO.UserResponse;
import dev.v.expensetracker.dto.userDTO.UserUpdateRequest;
import dev.v.expensetracker.security.CustomUserDetails;
import dev.v.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/")
    public UserResponse updateUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                                   @Valid @RequestBody UserUpdateRequest request) {
        Long userId = userDetails.getUser().getUserId();
        return userService.updateUser(userId, request);
    }

    @GetMapping("/")
    public UserResponse getUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getUserId();
        return userService.readUser(userId);
    }

    @DeleteMapping("/")
    public void deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getUserId();
        userService.deleteUser(userId);
    }
}
