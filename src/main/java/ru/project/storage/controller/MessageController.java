package ru.project.storage.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.storage.entity.Message;
import ru.project.storage.service.MessageService;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/new")
    public Map<String, Object> messageHandler(@RequestBody Message message, Principal principal) {
        if (message.getText().equals("history 10")) {
            return Collections.singletonMap("10 last messages: ", messageService.getHistoryMessages(message.getName()));
        }
        return messageService.saveNewMessage(message, principal);
    }
}
