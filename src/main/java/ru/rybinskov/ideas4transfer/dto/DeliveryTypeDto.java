package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.DeliveryType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryTypeDto {
    private Long id;
    private String type;

    public DeliveryTypeDto(DeliveryType deliveryType) {
        this.id = deliveryType.getId();
        this.type = deliveryType.getType();
    }

    public void updateAllFieldsWithoutId(DeliveryTypeDto deliveryTypeDto){
        this.type = deliveryTypeDto.getType();
    }
}
