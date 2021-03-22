package ru.rybinskov.ideas4transfer.domain.example;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.example.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Override

    List<Person> findAll();
}

