package ru.rybinskov.ideas4transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.dto.ShopDto;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shops_tbl")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String abbr;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Shop(ShopDto shopDto) {
        this.id = shopDto.getId();
        this.name = shopDto.getName();
        this.abbr = shopDto.getAbbr();
        this.brand = new Brand(shopDto.getBrand());
    }
}
