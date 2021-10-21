package ru.intech.chat.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.intech.chat.config.security.UserPrincipal;
import ru.intech.chat.model.User;
import ru.intech.chat.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
