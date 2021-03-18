package ru.rybinskov.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
