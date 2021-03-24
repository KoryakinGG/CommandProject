package ru.rybinskov.ideas4transfer.service.delivery_service;

import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;

import java.util.List;

public interface DeliveryService {

    List<DeliveryDto> findAll();
    DeliveryDto findById(Long id) throws ResourceNotFoundException;
    void createDelivery(DeliveryDto deliveryDto);
    void updateDelivery(DeliveryDto deliveryDto) throws ResourceNotFoundException;
    void delete(DeliveryDto deliveryDto);
    void saveAll(List<DeliveryDto> deliveryDtos);

}
