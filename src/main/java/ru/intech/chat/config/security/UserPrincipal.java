package ru.intech.chat.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.intech.chat.model.User;

public class UserPrincipal extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private final User user;

    public UserPrincipal(User user) {
        super(user.getLogin(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public static UserPrincipal safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof UserPrincipal) ? (UserPrincipal) principal : null;
    }

    public int getId() {
        return user.getId();
    }

    public String getUserName() {
        return user.getName();
    }
}
