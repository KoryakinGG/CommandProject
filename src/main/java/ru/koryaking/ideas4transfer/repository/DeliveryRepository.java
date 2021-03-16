package ru.koryaking.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryaking.ideas4transfer.domain.Delivery;

public interface DeliveryRepository extends JpaRepository <Delivery, Long> {
    Delivery findLandingById(Long id);
}
