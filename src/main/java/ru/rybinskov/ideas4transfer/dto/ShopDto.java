package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.Shop;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopDto {
    private Long id;
    private String name;
    private String abbr;
    private BrandDto brand;

    public ShopDto(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.abbr = shop.getAbbr();
        this.brand = new BrandDto(shop.getBrand());
    }

    public void updateAllFieldsWithoutId(ShopDto shopDto) {
        this.name = shopDto.getName();
        this.abbr = shopDto.getAbbr();
        this.brand = shopDto.getBrand();
    }
}
