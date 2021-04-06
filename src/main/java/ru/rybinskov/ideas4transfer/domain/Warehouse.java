package ru.rybinskov.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.dto.WarehouseDto;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "command_project", name = "warehouses_tbl")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String abbr;

    public Warehouse(WarehouseDto warehouseDto) {
        updateFields(warehouseDto);
    }

    public void updateFields(WarehouseDto warehouseDto) {
        this.id = warehouseDto.getId();
        this.name = warehouseDto.getName();
        this.abbr = warehouseDto.getAbbr();
    }

}
