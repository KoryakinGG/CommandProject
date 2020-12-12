package ru.rybinskov.ideas4transfer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybinskov.ideas4transfer.domain.order.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
}
