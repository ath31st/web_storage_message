package ru.project.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.storage.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
