package ru.rybinskov.ideas4transfer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.dto.*;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "deliveries_tbl")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
//            pattern = "yyyy.MM.dd")
            pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column
    private LocalDate deliveryDate;

    @ManyToOne
    private DeliveryTime deliveryTime;

    @Column
    private String carInfo;

    @Column
    private String driverInfo;

    @ManyToOne
    private Brand brand;

    @Column
    private String orderNumber;

    @ManyToOne
    private DeliveryType deliveryType;

    @Column
    private String sender;

    @Column
    private String comment;

    @ManyToOne
    private Shop shop;

    @Column
    private String numberOfPlaces;

    @Column
    private String torgNumber;

    @Column
    private String invoice;

    @ManyToOne
    private User user;

    @ManyToOne
    private Warehouse warehouse;

    public Delivery(DeliveryDto deliveryDto) {
        this.deliveryDate = deliveryDto.getDeliveryDate();
        this.deliveryTime = deliveryDto.getDeliveryTime();
        this.carInfo = deliveryDto.getCarInfo();
        this.driverInfo = deliveryDto.getDriverInfo();
        this.brand = new Brand(deliveryDto.getBrand());
        this.orderNumber = deliveryDto.getOrderNumber();
        this.deliveryType = deliveryDto.getDeliveryType();
        this.sender = deliveryDto.getSender();
        this.comment = deliveryDto.getComment();
        this.shop = new Shop(deliveryDto.getShop());
        this.numberOfPlaces = deliveryDto.getNumberOfPlaces();
        this.torgNumber = deliveryDto.getTorgNumber();
        this.invoice = deliveryDto.getInvoice();
        this.user = new User(deliveryDto.getUser());
        this.warehouse = new Warehouse(deliveryDto.getWarehouse());
    }
}
