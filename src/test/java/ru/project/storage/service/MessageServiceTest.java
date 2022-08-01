package ru.project.storage.service;

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

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MessageServiceTest {
    @Autowired
    MessageService messageService;

    @MockBean
    private  MessageRepository messageRepository;

    @MockBean
    private  UserRepository userRepository;

    Message message;
    User user;

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
    void saveNewMessage() {
        Mockito.when(userRepository.findByName(user.getName())).thenReturn(Optional.ofNullable(user));
        assertEquals("Hello", messageService.saveNewMessage(message, user.getName()).get("message"));
    }

    @Test
    void getHistoryMessages() {
        Mockito.when(messageRepository.findByUser_NameOrderByIdDesc(user.getName())).thenReturn(user.getMessages());
        assertNotNull(messageService.getHistoryMessages(user.getName()));
    }
}