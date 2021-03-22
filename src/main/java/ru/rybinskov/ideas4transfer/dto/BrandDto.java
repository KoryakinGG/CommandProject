package ru.rybinskov.ideas4transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rybinskov.ideas4transfer.domain.Brand;
import ru.rybinskov.ideas4transfer.domain.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDto {
    private Long id;
    private String name;
    private String abbr;

//    private List<Shop> shopsList;

    public BrandDto(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.abbr = brand.getAbbr();
    }
}