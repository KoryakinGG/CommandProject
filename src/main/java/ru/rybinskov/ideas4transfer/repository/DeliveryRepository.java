package ru.rybinskov.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.Delivery;
import ru.rybinskov.ideas4transfer.dto.DeliveryJson;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findDeliveryById(Long id);
}
