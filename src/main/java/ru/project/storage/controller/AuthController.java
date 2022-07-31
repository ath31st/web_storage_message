package ru.project.storage.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.storage.entity.User;
import ru.project.storage.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody User user) {
        return userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody User user) {
       return userService.getToken(user);
    }


}