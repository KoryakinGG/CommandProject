package ru.rybinskov.ideas4transfer.service.warehouse_service;

import ru.rybinskov.ideas4transfer.dto.WarehouseDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;

import java.util.List;

public interface WarehouseService {

    WarehouseDto findById(Long id) throws ResourceNotFoundException;
    List<WarehouseDto> findAll();
    void updateWarehouse(WarehouseDto warehouseDto) throws ResourceNotFoundException;
    WarehouseDto save(WarehouseDto warehouseDto) throws ResourceNotFoundException, WarehouseException;
    void delete(Long id);
    public void saveAll(List<WarehouseDto> warehouseDtos);
}
