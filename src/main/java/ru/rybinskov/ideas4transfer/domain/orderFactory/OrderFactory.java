package ru.rybinskov.ideas4transfer.domain.orderFactory;

import ru.rybinskov.ideas4transfer.domain.order.Order;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;

import java.time.LocalDateTime;

public interface OrderFactory {
    Order createOrder(Long id, String sender, String receiver, LocalDateTime created, OrderStatus orderStatus, Long userId);
}
