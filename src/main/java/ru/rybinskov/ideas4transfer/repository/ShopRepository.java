package ru.rybinskov.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findShopsByName(String name);
}
