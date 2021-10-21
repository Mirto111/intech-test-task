package ru.intech.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import ru.intech.chat.config.security.UserPrincipal;
import ru.intech.chat.dto.MessageDto;
import ru.intech.chat.model.Message;
import ru.intech.chat.repository.UserRepository;
import ru.intech.chat.service.MessageService;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    private final MessageService messageService;
    private final UserRepository userRepository;

    public ChatController(MessageService messageService, UserRepository userRepository) {
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    @MessageMapping("/send/message")
    @SendTo("/topic/chat")
    public MessageDto create(@Payload Message message, @AuthenticationPrincipal Authentication auth) {
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        message.setMsgDateTime(LocalDateTime.now().withNano(0).withSecond(0));
        message.setUser(userRepository.getById(principal.getId()));
        Message msg = messageService.create(message);
        return new MessageDto(msg.getId(), principal.getUserName(), msg.getText(), msg.getMsgDateTime());
    }
}
