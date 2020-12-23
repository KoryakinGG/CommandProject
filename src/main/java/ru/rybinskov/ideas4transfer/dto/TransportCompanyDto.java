package ru.rybinskov.ideas4transfer.dto;

import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;

import java.time.LocalDateTime;

public class TransportCompanyDto {
    //From TransferOrder
    private Long userId;
    private Long orderId;
    private String sender;
    private String receiver;

    //From OrderDetails without id
    private Long numberOfBoxes;
    private String comment;

    //From OrderDetails without id
    private String driverName;
    private String carNumber;
    private LocalDateTime shipmentDate;
    private LocalDateTime deliveryDate;
}
