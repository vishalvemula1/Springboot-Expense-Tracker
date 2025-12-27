package dev.v.expensetracker.controller;


import dev.v.expensetracker.dto.userDTO.UserCreateRequest;
import dev.v.expensetracker.dto.userDTO.UserResponse;
import dev.v.expensetracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public UserResponse registerUser(@Valid @RequestBody UserCreateRequest request) {
        return authService.createUser(request);
    }

    @PostMapping("/login")
    public JwtResponse authenticateUser(@RequestBody LoginRequest request) {
        return authService.authenticateUser(request);
    }
}
