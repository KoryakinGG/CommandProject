package ru.rybinskov.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.User;
import ru.rybinskov.ideas4transfer.dto.UserDto;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
