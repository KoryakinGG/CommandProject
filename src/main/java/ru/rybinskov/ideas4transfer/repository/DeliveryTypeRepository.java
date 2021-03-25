package ru.rybinskov.ideas4transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.DeliveryType;

public interface DeliveryTypeRepository extends JpaRepository <DeliveryType, Long> {
}
