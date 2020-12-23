package ru.rybinskov.ideas4transfer.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.rybinskov.ideas4transfer.domain.status_notification.EventManager;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TransferOrder extends EventManager implements Order {

    private Long id;

    private String sender;

    private String receiver;

    @DateTimeFormat
    private LocalDateTime created;

    private OrderStatus orderStatus;

    private Long userId;

    @Override
    public void changeStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        notify(orderStatus);
    }

    @Override
    public String getDescription() {
        return "Заявка на перевозку " + id + " с комментарием: ";
    }
}
