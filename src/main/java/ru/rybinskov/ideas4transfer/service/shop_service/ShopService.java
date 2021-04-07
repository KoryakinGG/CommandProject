package ru.rybinskov.ideas4transfer.service.shop_service;

import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;

import java.util.List;

public interface ShopService {

    ShopDto findById(Long id) throws ResourceNotFoundException;
    List<ShopDto> findAll();
    ShopDto save(ShopDto shopDto) throws ResourceNotFoundException, WarehouseException;
    void delete(Long id);
    void saveAll(List<ShopDto> shopDtos);
}
