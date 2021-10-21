package ru.intech.chat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.intech.chat.model.Role;
import ru.intech.chat.model.User;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void findByLogin() {
        User user = new User(null, "Aleksey", "user15", "pass", Set.of(Role.ROLE_USER));
        userService.create(user);
        assertEquals(user, userService.findByLogin(user.getLogin()));
    }

    @Test
    void create() {
        User user = new User(null, "Aleksey", "user5", "pass", Set.of(Role.ROLE_USER));
        userService.create(user);
        assertEquals(user, userService.findById(user.getId()));
    }
}