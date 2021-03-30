package ru.rybinskov.ideas4transfer.service.deleviry_type_service;

import ru.rybinskov.ideas4transfer.dto.DeliveryTypeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;

import java.util.List;

public interface DeliveryTypeService {
    List<DeliveryTypeDto> findAll();
    DeliveryTypeDto findById(Long id) throws ResourceNotFoundException;
    DeliveryTypeDto save(DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException, WarehouseException;
    void delete(Long id);
    void saveAll(List<DeliveryTypeDto> deliveryTypeDto);
}
