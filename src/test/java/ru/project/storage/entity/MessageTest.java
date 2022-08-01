package ru.project.storage.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    Message message;

    @BeforeEach
    void setUp() {
        User user = new User();
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
    }

    @Test
    void getId() {
        assertEquals(3L,message.getId());
    }

    @Test
    void getName() {
        assertEquals("Petr",message.getName());
    }

    @Test
    void getText() {
        assertEquals("Hello", message.getText());
    }

    @Test
    void getUser() {
        assertEquals(1L, message.getUser().getId());
    }

    @Test
    void setId() {
        message.setId(8L);
        assertEquals(8L,message.getId());
    }

    @Test
    void setName() {
        message.setName("Vasya");
        assertEquals("Vasya",message.getName());
    }

    @Test
    void setText() {
        message.setText("world");
        assertEquals("world",message.getText());
    }

    @Test
    void setUser() {
        User user = new User();
        user.setId(11L);
        user.setName("Nikolai");

        message.setUser(user);
        assertEquals(11L, message.getUser().getId());
    }

    @Test
    void testToString() {
        assertNotNull(message.toString());
    }
}