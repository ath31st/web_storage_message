package ru.project.storage.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Petr");
        user.setPassword("qwerty");

        Message message = new Message();
        message.setId(3L);
        message.setText("Hello");
        message.setName("Petr");
        message.setUser(user);

        List<Message> messages = user.getMessages();
        messages.add(message);
        user.setMessages(messages);
    }

    @Test
    void getId() {
        assertEquals(1L, (long) user.getId());
    }

    @Test
    void getName() {
        assertEquals("Petr", user.getName());
    }

    @Test
    void getPassword() {
        assertEquals("qwerty", user.getPassword());
    }

    @Test
    void getMessages() {
        assertNotNull(user.getMessages());
    }

    @Test
    void setId() {
        user.setId(5L);
        assertEquals(5L, user.getId());
    }

    @Test
    void setName() {
        user.setName("Vasya");
        assertEquals("Vasya", user.getName());
    }

    @Test
    void setPassword() {
        user.setPassword("asdfg");
        assertEquals("asdfg", user.getPassword());
    }

    @Test
    void setMessages() {
        Message message = new Message();
        message.setId(7L);
        message.setText("world");
        message.setName("Petr");
        message.setUser(user);

        List<Message> messages = user.getMessages();
        messages.add(message);

        assertEquals(2, user.getMessages().size());
    }

    @Test
    void testToString() {
        assertNotNull(user.toString());
    }
}