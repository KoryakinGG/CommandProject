package ru.rybinskov.ideas4transfer.orderFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.rybinskov.ideas4transfer.domain.order.*;
import java.time.LocalDateTime;

@Component
public class ExpressFactory implements OrderFactory {
    @Override
    public Order createOrder(Long id, LocalDateTime created, TransferOrderDetails orderDetails,
                             OrderType type, OrderStatus orderStatus, String comment) {
        return new ExpressDeliveryOrder(id, created, orderDetails, type, orderStatus, comment);
    }
}
