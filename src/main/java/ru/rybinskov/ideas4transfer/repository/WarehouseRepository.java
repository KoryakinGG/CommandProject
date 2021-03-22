package ru.rybinskov.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
