package ru.rybinskov.ideas4transfer.dto;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryJson {
    @JsonProperty(value = "id")
    private Long id;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty(value = "deliveryDate")
    private LocalDate deliveryDate;

    @JsonProperty(value = "deliveryTime")
//    @Enumerated(EnumType.STRING)
    private String deliveryTime;

    @JsonProperty(value = "carInfo")
    private String carInfo;

    @JsonProperty(value = "driverInfo")
    private String driverInfo;

    @JsonProperty(value = "brand")
    private String brand;

    @JsonProperty(value = "orderNumber")
    private String orderNumber;

    @JsonProperty(value = "deliveryType")
    private String deliveryType;

    @JsonProperty(value ="sender")
    private String sender;

    @JsonProperty(value = "comment")
    private String comment;

    @JsonProperty(value = "shop")
    private String shop;

    @JsonProperty(value = "numberOfPlaces")
    @Column
    private String numberOfPlaces;

    @JsonProperty(value = "torgNumber")
    @Column
    private String torgNumber;

    @JsonProperty(value = "invoice")
    private String invoice;

    public void updateAllFieldsWithoutId(DeliveryJson updatedDelivery) {
        this.deliveryDate = updatedDelivery.getDeliveryDate();
        this.deliveryTime = updatedDelivery.getDeliveryTime();
        this.carInfo = updatedDelivery.getCarInfo();
        this.driverInfo = updatedDelivery.getDriverInfo();
        this.brand = updatedDelivery.getBrand();
        this.orderNumber = updatedDelivery.getOrderNumber();
        this.deliveryType = updatedDelivery.getDeliveryType();
        this.sender = updatedDelivery.getSender();
        this.comment = updatedDelivery.getComment();
        this.shop = updatedDelivery.getShop();
        this.numberOfPlaces = updatedDelivery.getNumberOfPlaces();
        this.torgNumber = updatedDelivery.getTorgNumber();
        this.invoice = updatedDelivery.getInvoice();
    }

}
