package com.example.bookapi.controller;

import com.example.bookapi.dto.ApiResponse;
import com.example.bookapi.dto.UserDTO;
import com.example.bookapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody UserDTO dto) {

        UserDTO user = service.register(dto);

        return ResponseEntity.ok(
                ApiResponse.<UserDTO>builder()
                        .status("SUCCESS")
                        .code(200)
                        .message("User registered successfully")
                        .data(user)
                        .build()
        );
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserDTO dto) {

        String token = service.login(dto);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .status("SUCCESS")
                        .code(200)
                        .message("Login successful")
                        .data(token)
                        .build()
        );
    }
}