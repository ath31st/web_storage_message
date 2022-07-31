package ru.project.storage.service;

import org.springframework.stereotype.Service;
import ru.project.storage.entity.Message;
import ru.project.storage.repository.MessageRepository;
import ru.project.storage.repository.UserRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Map<String, Object> saveNewMessage(Message message, Principal principal) {
        message.setUser(userRepository.findByName(principal.getName()).get());
        messageRepository.save(message);
        return Collections.singletonMap("message", message.getText());
    }

    public List<Message> getHistoryMessages(String name) {
        return messageRepository.findByUser_Name(name)
               .stream()
               .limit(10)
               .toList();
    }
}
