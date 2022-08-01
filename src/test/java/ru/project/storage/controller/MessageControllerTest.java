package ru.project.storage.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.project.storage.entity.Message;
import ru.project.storage.entity.User;
import ru.project.storage.repository.MessageRepository;
import ru.project.storage.repository.UserRepository;
import ru.project.storage.service.MessageService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MessageControllerTest {

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    MessageController messageController;

    Message message;
    User user;
    Principal mockPrincipal = Mockito.mock(Principal.class);

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
    }

    @Test
    void messageHandler() {
        Mockito.when(mockPrincipal.getName()).thenReturn("Petr");
        Mockito.when(userRepository.findByName(user.getName())).thenReturn(Optional.ofNullable(user));
        messageController.messageHandler(message, mockPrincipal);
        assertEquals("Hello", messageController.messageHandler(message, mockPrincipal).get("message"));
    }
}