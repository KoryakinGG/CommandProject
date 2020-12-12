package ru.rybinskov.ideas4transfer.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.user.User;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transportation_order_details_tbl")
public class TransferOrderDetails {
    private static final String SEQ_NAME = "transportation_order_details_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @OneToOne
    @JoinColumn(name = "transportationOrder_id")
    private TransferOrder transferOrder;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User receiver;

    private String driver;

//    LocalDateTime shippingDate;
//
//    LocalDateTime unloadingDate;

    private Integer numberOfBoxes;

    private Double price;



}
