package ru.project.storage.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.project.storage.entity.User;
import ru.project.storage.repository.UserRepository;
import ru.project.storage.util.JWTUtil;

import java.util.Collections;
import java.util.Map;

@Service
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JWTUtil jwtUtil,
                       AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    public Map<String, Object> saveNewUser(User user){
        checkExistingUser(user);

        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getName());
        return Collections.singletonMap("token", token);
    }
    public Map<String, Object> getToken(User user){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(user.getName());

            return Collections.singletonMap("token", token);
        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Invalid Login Credentials");
        }
    }

    public void checkExistingUser(User user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User with name: " + user.getName() + " already exists!");
        }
    }
}