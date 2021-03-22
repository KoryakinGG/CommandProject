package ru.rybinskov.ideas4transfer.service.shop_service;

import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;

import java.util.List;

public interface ShopService {
    ShopDto findById(Long id) throws ResourceNotFoundException;
    List<ShopDto> findAll();
    void updateShop(ShopDto shopDto) throws ResourceNotFoundException;
    void save(ShopDto shopDto);
    void delete(ShopDto shopDto);
}
