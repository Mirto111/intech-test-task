package ru.intech.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.intech.chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
