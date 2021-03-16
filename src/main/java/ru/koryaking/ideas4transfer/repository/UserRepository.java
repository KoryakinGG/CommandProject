package ru.koryaking.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryaking.ideas4transfer.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
