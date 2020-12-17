package ru.rybinskov.ideas4transfer.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.user.User;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TransferOrderDetails {

    private Long id;

    private Long orderId;

    private User receiver;

    private String driver;

//    LocalDateTime shippingDate;
//
//    LocalDateTime unloadingDate;

    private Integer numberOfBoxes;

    private Double price;

}
