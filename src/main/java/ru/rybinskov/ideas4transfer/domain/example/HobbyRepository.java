package ru.rybinskov.ideas4transfer.domain.example;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.example.Hobby;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
