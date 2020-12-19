package ru.rybinskov.ideas4transfer.bootstrap;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.rybinskov.ideas4transfer.dao.MockBD;
import ru.rybinskov.ideas4transfer.domain.status_notification.EmailNotificationListener;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;
import ru.rybinskov.ideas4transfer.domain.order.OrderType;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrderDetails;
import ru.rybinskov.ideas4transfer.domain.user.User;
import ru.rybinskov.ideas4transfer.service.order_service.OrderService;

import java.time.LocalDateTime;


@Component
public class DataInit implements CommandLineRunner {

    public static final User USER = new User();
    public static final User MANAGER = new User();

    static {
        USER.setName("Alex");
        USER.setEmail("user@ideas4transfer.ru");
        MANAGER.setName("John");
        MANAGER.setEmail("manager@ideas4transfer.ru");
    }

    public final OrderService orderService;

    public DataInit(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void init() {
        TransferOrder order = new TransferOrder(1L, USER.getName(), LocalDateTime.now(),
                new TransferOrderDetails(1L, 1L, MANAGER, "Anton", 35, 13000.00),
                OrderType.BETWEEN_STORES, OrderStatus.NEW, "GO go go");
        order.subscribe(new EmailNotificationListener(order.getId(), USER.getEmail()));
        order.subscribe(new EmailNotificationListener(order.getId(), MANAGER.getEmail()));
        MockBD.addOrder(order);
    }

    @Override
    public void run(String... args) throws Exception {
        init();
    }
}
