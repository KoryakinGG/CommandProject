package ru.rybinskov.ideas4transfer.service.warehouse_service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rybinskov.ideas4transfer.domain.Shop;
import ru.rybinskov.ideas4transfer.domain.Warehouse;
import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.dto.WarehouseDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.WarehouseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;

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
    public WarehouseDto save(WarehouseDto warehouseDto) throws ResourceNotFoundException, WarehouseException {
        if (warehouseDto.getId() == null) {
            return new WarehouseDto(warehouseRepository.save(new Warehouse(warehouseDto)));
        }
        Warehouse warehouse = warehouseRepository.findById(warehouseDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Склад с id = " + warehouseDto.getId() + " не найден"));
        warehouse.updateFields(warehouseDto);
        return new WarehouseDto(warehouseRepository.save(warehouse));
    }

    @Override
    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<WarehouseDto> warehouseDtos) {
        List<Warehouse> shops = warehouseDtos.stream().map(Warehouse::new).collect(Collectors.toList());
        warehouseRepository.saveAll(shops);
    }
}
