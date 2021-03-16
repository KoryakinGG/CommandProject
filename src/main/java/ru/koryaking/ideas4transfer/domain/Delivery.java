package ru.koryaking.ideas4transfer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project_v2", name = "delivery_tbl")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @JsonFormat(
//            shape = JsonFormat.Shape.STRING,
//            pattern = "dd.MM.yyyy")
    @Column (name = "delivery_date")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
//    @JsonProperty(value = "0")
    private LocalDate deliveryDate;
//    @JsonProperty(value = "1")
    @Enumerated(EnumType.STRING)
    @Column (name = "delivery_time")
    private DeliveryTime deliveryTime;
//    @JsonProperty(value = "3")
    @Column (name = "car_info")
    private String carInfo;
//    @JsonProperty(value = "4")
    @Column (name = "drivers_info")
    private String driverInfo;
//    @JsonProperty(value = "5")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;           //Проблема в том, что поля сущности должны быть типизированы строками, цифрами и т.д.
//    @JsonProperty(value = "6")
    @Column (name = "order_Number")
    private String orderNumber;
//    @JsonProperty(value = "7")
    @Enumerated(EnumType.STRING)
    @Column (name = "delivery_type")
    private DeliveryType deliveryType;
//    @JsonProperty(value = "8")
    @Column (name = "sendor")
    private String sendor;
//    @JsonProperty(value = "9")
    @Column (name = "comment")
    private String comment;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;
//    @JsonProperty(value = "10")
    @Column (name = "number_of_places")
    private String numberOfPlaces;
    @Column (name = "torg_number")
//    @JsonProperty(value = "11")
    private String torgNumber;
//    @JsonProperty(value = "12")
    @Column (name = "invoice")
    private String invoice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
