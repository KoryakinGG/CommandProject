package ru.rybinskov.ideas4transfer.service.order_service;

import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;
import ru.rybinskov.ideas4transfer.dto.OrderViewDto;
import ru.rybinskov.ideas4transfer.dto.SimpleViewDto;

import java.util.List;


public interface OrderService {

    // String getOrderDescription(String type);

    OrderViewDto getOrderById(Long id);

    List<SimpleViewDto> getAllSimplifiedViewOrdersByUser(String username);

    List<OrderViewDto> getAll();

    void save(OrderViewDto order);

    void update(OrderViewDto order);
}
