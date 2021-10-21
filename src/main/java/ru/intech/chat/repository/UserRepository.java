package ru.intech.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.intech.chat.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
