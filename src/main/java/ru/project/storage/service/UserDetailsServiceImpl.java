package ru.project.storage.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.project.storage.entity.User;
import ru.project.storage.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByName(name);
        if (userOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with name: " + name + " not found!");
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(
                name,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
