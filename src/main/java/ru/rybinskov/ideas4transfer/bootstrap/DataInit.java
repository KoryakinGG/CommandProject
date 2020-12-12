package ru.rybinskov.ideas4transfer.bootstrap;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.rybinskov.ideas4transfer.dao.OrderDao;
import ru.rybinskov.ideas4transfer.dao.UserDao;
import ru.rybinskov.ideas4transfer.domain.user.Role;
import ru.rybinskov.ideas4transfer.domain.user.User;
import ru.rybinskov.ideas4transfer.service.order_service.OrderService;


@Component
public class DataInit implements CommandLineRunner {

    public static final User USER = new User();
    public static final User MANAGER = new User();

    public final OrderDao orderDao;
    public final UserDao userDao;
    public final OrderService orderService;


    static {
        USER.setName("User");
        USER.setPassword("user123");
        USER.setRole(Role.USER);

        MANAGER.setName("Manager");
        MANAGER.setPassword("manager123");
        MANAGER.setRole(Role.MANAGER);

    }

    public DataInit(OrderDao orderDao, UserDao userDao, OrderService orderService) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.orderService = orderService;
    }


    public static void init(OrderDao orderDao, UserDao userDao) {
        userDao.save(USER);
        userDao.save(MANAGER);
    }

    @Override
    public void run(String... args) throws Exception {
        init(orderDao, userDao);
    }
}
