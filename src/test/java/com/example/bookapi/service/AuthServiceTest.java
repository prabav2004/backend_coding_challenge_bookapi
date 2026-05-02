package com.example.bookapi.service;

import com.example.bookapi.dto.UserDTO;
import com.example.bookapi.entity.Role;
import com.example.bookapi.entity.User;
import com.example.bookapi.repository.UserRepository;
import com.example.bookapi.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository repo;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService service;

    public AuthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {

        UserDTO dto = UserDTO.builder()
                .username("admin")
                .password("1234")
                .role("ADMIN")
                .build();

        when(repo.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        UserDTO result = service.register(dto);

        assertEquals("admin", result.getUsername());
    }

    @Test
    void testLogin() {

        User user = User.builder()
                .username("admin")
                .password(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"))
                .role(Role.ADMIN)
                .build();

        when(repo.findById("admin")).thenReturn(java.util.Optional.of(user));
        when(jwtUtil.generateToken("admin")).thenReturn("dummy-token");

        String token = service.login(
                UserDTO.builder()
                        .username("admin")
                        .password("1234")
                        .build()
        );

        assertEquals("dummy-token", token);
    }
}