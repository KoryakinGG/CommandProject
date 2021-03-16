package ru.koryaking.ideas4transfer.service.user_service;

import ru.koryaking.ideas4transfer.domain.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    List<User> getAll();
    User findByName(String name);
    void save(User user);
}
