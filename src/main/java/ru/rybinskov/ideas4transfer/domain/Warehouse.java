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
@Table(name = "warehouses_tbl")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String abbr;

    public Warehouse(WarehouseDto warehouse) {
        this.id = warehouse.getId();
        this.name = warehouse.getName();
        this.abbr = warehouse.getAbbr();
    }

}
