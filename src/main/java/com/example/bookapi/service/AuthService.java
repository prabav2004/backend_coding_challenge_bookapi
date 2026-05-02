package com.example.bookapi.service;

import com.example.bookapi.dto.UserDTO;
import com.example.bookapi.entity.User;
import com.example.bookapi.exception.ResourceNotFoundException;
import com.example.bookapi.mapper.UserMapper;
import com.example.bookapi.repository.UserRepository;
import com.example.bookapi.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER
    public UserDTO register(UserDTO dto) {

        User user = UserMapper.toEntity(dto);

        // Encrypt password
        user.setPassword(encoder.encode(user.getPassword()));

        return UserMapper.toDTO(repo.save(user));
    }

    // LOGIN
    public String login(UserDTO dto) {

        User user = repo.findById(dto.getUsername())
        .orElseThrow(() ->
            new ResourceNotFoundException("User not found")
        );

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}