package ru.rybinskov.ideas4transfer.domain.status_notification;

import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;

public interface Observer {
    void update(EventManager eventManager, OrderStatus status);
}
