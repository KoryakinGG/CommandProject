package ru.rybinskov.ideas4transfer.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TransferOrder implements Order {

    private Long id;

    private String sender;

    private LocalDateTime created;

    private TransferOrderDetails orderDetails;

    private OrderType type;

    private OrderStatus orderStatus;

    private String comment;


    @Override
    public String getDescription() {
        return "Заявка на перевозку " + id + " с комментарием: " + comment;
    }
}
