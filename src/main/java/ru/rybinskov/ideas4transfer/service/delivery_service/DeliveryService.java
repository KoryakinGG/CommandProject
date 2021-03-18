package ru.rybinskov.ideas4transfer.service.delivery_service;

import ru.rybinskov.ideas4transfer.domain.Delivery;
import ru.rybinskov.ideas4transfer.dto.DeliveryJson;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {

//    Delivery DeliveryById(Long id);
//    void save(Delivery delivery);
//    List<Delivery> getAll();
//    List<Delivery> getAllSimplifiedLandingByUser(String username);
//    void saveAll(List<Delivery> list);
//    void deleteById (Long id);

//    void collectAndSaveDelivery(DeliveryJson deliveryJson);
    List<DeliveryJson> findAll();
    DeliveryJson findById(Long id) throws ResourceNotFoundException;
    DeliveryJson findByJson(DeliveryJson deliveryJson) throws ResourceNotFoundException;
    void createDelivery(DeliveryJson deliveryJson);
    void updateDelivery(DeliveryJson deliveryJson);
    void delete(DeliveryJson deliveryJson);

}
