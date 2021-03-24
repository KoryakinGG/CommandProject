package ru.rybinskov.ideas4transfer.service.brand_service;

import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;

import java.util.List;

public interface BrandService {

    List<BrandDto> findAll();
    BrandDto findById(Long id) throws ResourceNotFoundException;
    void createBrand(BrandDto brandDto);
    void updateBrand(BrandDto brandDto) throws ResourceNotFoundException;
    void delete(BrandDto brandDto);
    void saveAll(List<BrandDto> brandDtos);
}
