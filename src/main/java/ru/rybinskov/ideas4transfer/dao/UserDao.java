package ru.rybinskov.ideas4transfer.dao;

import ru.rybinskov.ideas4transfer.domain.user.User;

public interface UserDao {
    User findFirstByName(String username);
}
