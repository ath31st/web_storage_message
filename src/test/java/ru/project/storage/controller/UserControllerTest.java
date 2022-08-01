package ru.project.storage.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import ru.project.storage.entity.Message;
import ru.project.storage.entity.User;
import ru.project.storage.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@WithMockUser(username = "Petr", password = "qwerty", roles = "USER")
class UserControllerTest {

    @Autowired
    UserController userController;

    @MockBean
    UserRepository userRepository;

    @Mock
    private Authentication auth;

    User user;
    Message message;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Petr");
        user.setPassword("qwerty");

        message = new Message();
        message.setId(3L);
        message.setText("Hello");
        message.setName("Petr");
        message.setUser(user);

        List<Message> messages = user.getMessages();
        messages.add(message);
        user.setMessages(messages);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void getUserDetails() {
        Mockito.when((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn("Petr");
        Mockito.when(userRepository.findByName(user.getName())).thenReturn(Optional.ofNullable(user));
        assertEquals(userController.getUserDetails(), user);
    }
}