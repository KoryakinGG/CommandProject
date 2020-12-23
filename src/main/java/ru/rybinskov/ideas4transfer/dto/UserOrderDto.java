package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDto {
    //From TransferOrder
    private Long userId;
    private Long orderId;
    private String sender;
    private String receiver;
    private LocalDateTime created;
    private OrderStatus orderStatus;

    //From OrderDetails without id
    private Long numberOfBoxes;
    private String comment;
}
