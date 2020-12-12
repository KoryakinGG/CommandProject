package ru.rybinskov.ideas4transfer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.user.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findFirstByName(String username);
}
