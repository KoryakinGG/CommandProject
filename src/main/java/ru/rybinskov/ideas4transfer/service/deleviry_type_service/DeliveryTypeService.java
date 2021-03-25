package ru.rybinskov.ideas4transfer.service.deleviry_type_service;

import ru.rybinskov.ideas4transfer.dto.DeliveryTypeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;

import java.util.List;

public interface DeliveryTypeService {
    List<DeliveryTypeDto> findAll();
    DeliveryTypeDto findById(Long id) throws ResourceNotFoundException;
    void createDeliveryType(DeliveryTypeDto deliveryTypeDto);
    void updateDeliveryType(DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException;
    void delete(DeliveryTypeDto deliveryTypeDto);
    void saveAll(List<DeliveryTypeDto> deliveryTypeDto);
}
