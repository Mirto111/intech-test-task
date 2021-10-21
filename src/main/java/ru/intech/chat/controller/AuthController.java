package ru.intech.chat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.intech.chat.config.security.UserPrincipal;
import ru.intech.chat.dto.MessageDto;
import ru.intech.chat.model.User;
import ru.intech.chat.service.MessageService;
import ru.intech.chat.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController {

    private final UserService userService;
    private final MessageService messageService;
    private final PasswordEncoder encoder;

    public AuthController(UserService userService, MessageService messageService, PasswordEncoder encoder) {
        this.userService = userService;
        this.messageService = messageService;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "registration";
    }

    @PostMapping("/register")
    public String registration(User user, ModelMap modelMap) {
        if (userService.findByLogin(user.getLogin()) != null) {
            modelMap.addAttribute("registerError", "Пользователь с таким логином уже есть!");
            return "registration";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userService.create(user);
        return "redirect:login?register=true";
    }

    @GetMapping("/chat")
    public String chat(ModelMap modelMap) {
        List<MessageDto> messages = messageService.getMessages().stream()
                .map(m -> new MessageDto(m.getId(), m.getUser().getName(), m.getText(), m.getMsgDateTime())).collect(Collectors.toList());
        modelMap.addAttribute("username", UserPrincipal.safeGet().getUserName());
        modelMap.addAttribute("messages", messages);
        return "chat";
    }

}
