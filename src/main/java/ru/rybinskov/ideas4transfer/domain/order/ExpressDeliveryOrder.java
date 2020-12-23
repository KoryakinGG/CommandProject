package ru.rybinskov.ideas4transfer.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.status_notification.EventManager;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExpressDeliveryOrder extends EventManager implements Order {

    private Long id;

    private String sender;

    private String receiver;

    private LocalDateTime created;

    private OrderStatus orderStatus;

    private Long userId;

    @Override
    public String getDescription() {
        return "Заявка на курьерскую доставку " + id + " с комментарием: ";
    }

    @Override
    public void changeStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        notify(orderStatus);
    }
}
