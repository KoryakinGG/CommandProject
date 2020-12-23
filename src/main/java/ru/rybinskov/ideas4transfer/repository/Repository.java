package ru.rybinskov.ideas4transfer.repository;

import ru.rybinskov.ideas4transfer.dto.OrderViewDto;

import java.util.List;

public interface Repository<T> {
    T findById(Long id);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();
}
