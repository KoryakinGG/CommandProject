package ru.koryaking.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryaking.ideas4transfer.domain.Shop;

public interface ShopRepository extends JpaRepository <Shop, Long> {
    Shop findShopById (Long id);
}
