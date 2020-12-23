package ru.rybinskov.ideas4transfer.dto;

import lombok.Builder;
import lombok.Data;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class SimpleViewDto {
    //From TransferOrder
    private Long orderId;
    private String sender;
    private String receiver;
    private LocalDateTime shipmentDate;
    private OrderStatus orderStatus;
}
