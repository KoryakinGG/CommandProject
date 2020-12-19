package ru.rybinskov.ideas4transfer.domain.status_notification;

import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;

public class EmailNotificationListener implements Observer {
    private String email;
    private Long id;

    public EmailNotificationListener(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public void update(EventManager eventManager, OrderStatus status) {
        System.out.println("Отправляем email по адресу: " + email + ", оповещая о том, что статус " +
                "заказа: " + id + " стал :" + status);
    }
}
