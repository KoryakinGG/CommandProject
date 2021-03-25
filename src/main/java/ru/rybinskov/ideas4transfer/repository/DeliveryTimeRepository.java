package ru.rybinskov.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.DeliveryTime;

public interface DeliveryTimeRepository extends JpaRepository <DeliveryTime, Long> {
}
