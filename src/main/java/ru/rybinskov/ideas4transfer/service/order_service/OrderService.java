package ru.rybinskov.ideas4transfer.service.order_service;

import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;


public interface OrderService {

    // String getOrderDescription(String type);

    TransferOrder getOrderById(Long id);

}
