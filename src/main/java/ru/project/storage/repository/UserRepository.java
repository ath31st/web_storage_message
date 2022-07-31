package ru.project.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.storage.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByName(String name);
}
