package ru.rybinskov.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
