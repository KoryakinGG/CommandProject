package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderViewDto {
    //From TransferOrder
    private Long orderId;
    private String sender;
    private String receiver;
    private LocalDateTime created;
    private OrderStatus orderStatus;
    private Long userId;

    //From OrderDetails without id
    private Long numberOfBoxes;
    private String comment;

    //From OrderDetails without id
    private String driverName;
    private String carNumber;
    private LocalDateTime shipmentDate;
    private LocalDateTime deliveryDate;
}
