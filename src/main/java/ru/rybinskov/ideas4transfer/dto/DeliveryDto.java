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
import ru.rybinskov.ideas4transfer.domain.Delivery;
import ru.rybinskov.ideas4transfer.domain.DeliveryTime;
import ru.rybinskov.ideas4transfer.domain.DeliveryType;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDto {
    @JsonProperty(value = "id")
    private Long id;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
//            pattern = "yyyy.MM.dd")
            pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty(value = "deliveryDate")
    private LocalDate deliveryDate;
    @JsonProperty(value = "deliverTime")
    private DeliveryTime deliveryTime;

    @JsonProperty(value = "carInfo")
    private String carInfo;

    @JsonProperty(value = "driverInfo")
    private String driverInfo;

    @JsonProperty(value = "brand")
    private BrandDto brand;

    @JsonProperty(value = "orderNumber")
    private String orderNumber;

    @JsonProperty(value = "deliveryType")
    private DeliveryType deliveryType;

    @JsonProperty(value ="sender")
    private String sender;

    @JsonProperty(value = "comment")
    private String comment;

    @JsonProperty(value = "shop")
    private ShopDto shop;

    @JsonProperty(value = "numberOfPlaces")
    @Column
    private String numberOfPlaces;

    @JsonProperty(value = "torgNumber")
    @Column
    private String torgNumber;

    @JsonProperty(value = "invoice")
    private String invoice;

    @JsonProperty(value = "user")
    private UserDto user;

    @JsonProperty(value = "warehouse")
    private WarehouseDto warehouse;

    public DeliveryDto(Delivery delivery) {
        this.id = delivery.getId();
        this.deliveryDate = delivery.getDeliveryDate();
        this.deliveryTime = delivery.getDeliveryTime();
        this.carInfo = delivery.getCarInfo();
        this.driverInfo = delivery.getDriverInfo();
        this.brand = new BrandDto(delivery.getBrand());
        this.orderNumber = delivery.getOrderNumber();
        this.deliveryType = delivery.getDeliveryType();
        this.sender = delivery.getSender();
        this.comment = delivery.getComment();
        this.shop = new ShopDto(delivery.getShop());
        this.numberOfPlaces = delivery.getNumberOfPlaces();
        this.torgNumber = delivery.getTorgNumber();
        this.invoice = delivery.getInvoice();
        this.user = new UserDto(delivery.getUser());
        this.warehouse = new WarehouseDto(delivery.getWarehouse());
    }


    public void updateAllFieldsWithoutId(DeliveryDto updatedDelivery) {
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
        this.user = updatedDelivery.getUser();
        this.warehouse = updatedDelivery.getWarehouse();
    }

}
