package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.Warehouse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDto {

    private Long id;
    private String name;
    private String abbr;

    public WarehouseDto(Warehouse warehouse) {
        this.id = warehouse.getId();
        this.name = warehouse.getName();
        this.abbr = warehouse.getAbbr();
    }

    public void updateAllFieldsWithoutId(WarehouseDto warehouseDto) {
        this.name = warehouseDto.getName();
        this.abbr = warehouseDto.getAbbr();
    }

}
