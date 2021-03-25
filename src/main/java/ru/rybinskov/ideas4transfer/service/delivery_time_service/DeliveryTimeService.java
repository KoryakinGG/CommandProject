package ru.rybinskov.ideas4transfer.service.delivery_time_service;

import ru.rybinskov.ideas4transfer.dto.DeliveryTimeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;

import java.util.List;


public interface DeliveryTimeService {
    List<DeliveryTimeDto> findAll();
    DeliveryTimeDto findById(Long id) throws ResourceNotFoundException;
    void createDeliveryTime(DeliveryTimeDto deliveryTimeDto);
    void updateDeliveryTime(DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException;
    void delete(DeliveryTimeDto deliveryTimeDto);
    void saveAll(List<DeliveryTimeDto> deliveryTimeDto);
}
