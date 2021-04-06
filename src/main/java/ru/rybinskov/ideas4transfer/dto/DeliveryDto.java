package ru.rybinskov.ideas4transfer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDto {

    private Long id;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deliveryDate;

    private DeliveryTime deliveryTime;

    private String carInfo;

    private String driverInfo;

    private BrandDto brand;

    private String orderNumber;

    private DeliveryType deliveryType;

    private String sender;

    private String comment;

    private ShopDto shop;

    private String numberOfPlaces;

    private String torgNumber;

    private String invoice;

    private UserDto user;

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

//    public void updateAllFieldsWithoutId(DeliveryDto updatedDelivery) {
//        this.deliveryDate = updatedDelivery.getDeliveryDate();
//        this.deliveryTime = updatedDelivery.getDeliveryTime();
//        this.carInfo = updatedDelivery.getCarInfo();
//        this.driverInfo = updatedDelivery.getDriverInfo();
//        this.brand = updatedDelivery.getBrand();
//        this.orderNumber = updatedDelivery.getOrderNumber();
//        this.deliveryType = updatedDelivery.getDeliveryType();
//        this.sender = updatedDelivery.getSender();
//        this.comment = updatedDelivery.getComment();
//        this.shop = updatedDelivery.getShop();
//        this.numberOfPlaces = updatedDelivery.getNumberOfPlaces();
//        this.torgNumber = updatedDelivery.getTorgNumber();
//        this.invoice = updatedDelivery.getInvoice();
//        this.user = updatedDelivery.getUser();
//        this.warehouse = updatedDelivery.getWarehouse();
//    }
}
