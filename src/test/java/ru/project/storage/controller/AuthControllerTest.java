package ru.project.storage.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import ru.project.storage.entity.User;
import ru.project.storage.repository.UserRepository;
import ru.project.storage.service.UserService;
import ru.project.storage.util.JWTUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AuthControllerTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    UserService userService;
    @Autowired
    AuthController authController;
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Petr");
        user.setPassword("asdfg");
    }

    @Test
    void registerHandler() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(152, authController.registerHandler(user).get("token").toString().length());
    }

    @Test
    void loginHandler() {
        assertThrows(ResponseStatusException.class, () -> authController.loginHandler(user).get("token").toString().length());
    }
}