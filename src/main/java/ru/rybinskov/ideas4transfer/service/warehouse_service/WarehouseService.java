package ru.rybinskov.ideas4transfer.service.warehouse_service;

import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.dto.WarehouseDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.WarehouseRepository;

import java.util.List;

public interface WarehouseService {

    WarehouseDto findById(Long id) throws ResourceNotFoundException;
    List<WarehouseDto> findAll();
    void updateWarehouse(WarehouseDto warehouseDto) throws ResourceNotFoundException;
    void save(WarehouseDto warehouseDto);
    void delete(WarehouseDto warehouseDto);
}
