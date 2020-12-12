package ru.rybinskov.ideas4transfer.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.rybinskov.ideas4transfer.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transportation_orders_tbl")
public class TransferOrder implements Order {
    private static final String SEQ_NAME = "transportation_order_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;

    @CreationTimestamp
    private LocalDateTime created;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User sender;

    @OneToOne(mappedBy = "transportationOrder")
    private TransferOrderDetails orderDetails;


    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String comment;

    @Override
    public String getDescription() {
        return "Заявка на перевозку " + id + " с комментарием: " + comment;
    }
}
