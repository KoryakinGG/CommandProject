package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.DeliveryTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryTimeDto {
    private Long id;
    private String deliveryTime;

    public DeliveryTimeDto(DeliveryTime deliveryTime) {
        this.id = deliveryTime.getId();
        this.deliveryTime = deliveryTime.getDeliveryTime();
    }

    public void updateAllFieldsWithoutId(DeliveryTimeDto deliveryTimeDto) {
        this.deliveryTime = deliveryTimeDto.getDeliveryTime();
    }

}
