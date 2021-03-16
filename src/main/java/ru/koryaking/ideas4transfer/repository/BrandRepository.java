package ru.koryaking.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryaking.ideas4transfer.domain.Brand;

public interface BrandRepository extends JpaRepository <Brand, Long> {
    Brand findBrandById (Long id);
}
