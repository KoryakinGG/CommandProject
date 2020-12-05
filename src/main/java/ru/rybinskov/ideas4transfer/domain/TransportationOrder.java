package ru.rybinskov.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders_tbl")
public class Order {
    private static final String SEQ_NAME = "order_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime changed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;

    private User receiver;

    private LocalDateTime shippingDate;
    private LocalDateTime unloadingDate;
    private Integer numberOfBoxes;
    private Integer volume;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Enumerated(EnumType.STRING)
    private Status status;
}
