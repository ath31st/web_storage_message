package ru.project.storage.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.storage.entity.User;
import ru.project.storage.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/info")
    public User getUserDetails() {
        String name = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByName(name).get();
    }
}






