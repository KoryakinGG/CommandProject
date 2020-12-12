package ru.rybinskov.ideas4transfer.orderFactory;

import org.springframework.context.annotation.Bean;
import ru.rybinskov.ideas4transfer.domain.order.Order;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;
import ru.rybinskov.ideas4transfer.domain.order.OrderType;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrderDetails;

import java.time.LocalDateTime;

public interface OrderFactory {
    Order createOrder(Long id, LocalDateTime created, TransferOrderDetails orderDetails,
                      OrderType type, OrderStatus orderStatus, String comment);
}
