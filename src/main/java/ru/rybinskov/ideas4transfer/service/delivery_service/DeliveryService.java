package ru.rybinskov.ideas4transfer.service.delivery_service;

import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;

import java.util.List;

public interface DeliveryService {

//    Delivery DeliveryById(Long id);
//    void save(Delivery delivery);
//    List<Delivery> getAll();
//    List<Delivery> getAllSimplifiedLandingByUser(String username);
//    void saveAll(List<Delivery> list);
//    void deleteById (Long id);

//    void collectAndSaveDelivery(DeliveryJson deliveryJson);
    List<DeliveryDto> findAll();
    DeliveryDto findById(Long id) throws ResourceNotFoundException;
    void createDelivery(DeliveryDto deliveryDto);
    void updateDelivery(DeliveryDto deliveryDto);
    void delete(DeliveryDto deliveryDto);

}
