package ru.rybinskov.ideas4transfer.service.user_service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.rybinskov.ideas4transfer.domain.user.User;


import java.util.List;

public interface UserService extends UserDetailsService {
    User getById(Long id);
    User auth(String name, String password);
    List<User> getAll();
    User findByName(String name);
    void save(User user);
}
