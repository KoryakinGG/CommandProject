package ru.rybinskov.ideas4transfer.service.order_service;

import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.dao.OrderDao;
import ru.rybinskov.ideas4transfer.domain.order.Order;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;
import ru.rybinskov.ideas4transfer.domain.order.OrderType;
import ru.rybinskov.ideas4transfer.orderFactory.ExpressFactory;
import ru.rybinskov.ideas4transfer.orderFactory.OrderFactory;
import ru.rybinskov.ideas4transfer.orderFactory.TransferFactory;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    OrderFactory factory;

    // OrderDao orderDao;

    public OrderServiceImpl(OrderFactory factory) {
        this.factory = factory;
        //     this.orderDao = orderDao;
    }

    @Override
    public String getOrderDescription() {
        return factory.createOrder(3L, LocalDateTime.now(), null,
                OrderType.BETWEEN_STORES, OrderStatus.NEW, "Работает!").getDescription();
    }

//      Для проверки работоспособности
//    public static void main(String[] args) {
//        OrderService orderService = new OrderServiceImpl(new ExpressFactory());
//        System.out.println(orderService.getOrderDescription());
//    }
}
