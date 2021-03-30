package ru.rybinskov.ideas4transfer.service.brand_service;

import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;

import java.util.List;

public interface BrandService {

    List<BrandDto> findAll();
    BrandDto findById(Long id) throws ResourceNotFoundException;
    void delete(Long id);
    void saveAll(List<BrandDto> brandDtos);
    BrandDto save(BrandDto brandDto) throws ResourceNotFoundException, WarehouseException;
}
