package ru.rybinskov.ideas4transfer.orderFactory;


import ru.rybinskov.ideas4transfer.domain.order.*;
import java.time.LocalDateTime;

public class TransferFactory implements OrderFactory {
    @Override
    public Order createOrder(Long id, String sender, LocalDateTime created, TransferOrderDetails orderDetails,
                             OrderType type, OrderStatus orderStatus, String comment) {
        return new TransferOrder(id, sender, created, orderDetails, type, orderStatus, comment);
    }
}
