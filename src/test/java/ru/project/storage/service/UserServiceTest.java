package ru.project.storage.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import ru.project.storage.util.JWTUtil;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    User user;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1L);
        user.setName("Petr");
        user.setPassword("qwerty");
        user.setMessages(new ArrayList<>());
    }

    @Test
    void saveNewUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(152, userService.saveNewUser(user).get("token").toString().length());
    }

    @Test
    void getToken() {
        assertThrows(ResponseStatusException.class, () -> userService.getToken(user).get("token").toString().length());
    }
}