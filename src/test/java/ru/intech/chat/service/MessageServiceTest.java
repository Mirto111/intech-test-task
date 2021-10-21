package ru.intech.chat.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.intech.chat.model.Message;
import ru.intech.chat.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Autowired
    UserRepository userRepository;

    @Test
    void create() {
        LocalDateTime lcd = LocalDateTime.now().withNano(0).withSecond(0);
        Message message = new Message(null, null, "новое сообщение", lcd);
        message.setUser(userRepository.getById(1));
        messageService.create(message);
        Assertions.assertEquals(message, messageService.findById(message.getId()));
    }

    @Test
    void getMessages() {
        List<Message> messages = messageService.getMessages();
        assertTrue(messages.size()>2);
    }
}