package ru.rybinskov.ideas4transfer.service.warehouse_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rybinskov.ideas4transfer.domain.Warehouse;
import ru.rybinskov.ideas4transfer.dto.WarehouseDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.WarehouseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    private WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public WarehouseDto findById(Long id) throws ResourceNotFoundException {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Склад по указанному id не найден: id = " + id));
        return new WarehouseDto(warehouse);
    }

    @Override
    public List<WarehouseDto> findAll() {
        return warehouseRepository.findAll().stream().map(WarehouseDto::new).collect(Collectors.toList());
    }

    @Override
    public void updateWarehouse(WarehouseDto warehouseDto) throws ResourceNotFoundException {
        WarehouseDto warehouse = findById(warehouseDto.getId());
        warehouse.updateAllFieldsWithoutId(warehouseDto);
        warehouseRepository.save(new Warehouse(warehouse));
    }

    @Override
    public void save(WarehouseDto warehouseDto) {
        warehouseRepository.save(new Warehouse(warehouseDto));
    }

    @Override
    public void delete(WarehouseDto warehouseDto) {
        warehouseRepository.delete(new Warehouse(warehouseDto));
    }
}
