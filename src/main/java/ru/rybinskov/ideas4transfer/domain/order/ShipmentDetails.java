package ru.rybinskov.ideas4transfer.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentDetails {

    private Long id;

    private String driverName;

    private String carNumber;

    @DateTimeFormat
    private LocalDateTime shipmentDate;

    @DateTimeFormat
    private LocalDateTime deliveryDate;

    private Long orderId;
}

