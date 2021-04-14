package ru.rybinskov.ideas4transfer.service.delivery_service;

import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.dto.UniqueReportObject;
import ru.rybinskov.ideas4transfer.exception.ExceedingAllowedDateValueException;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;

import java.util.List;

public interface DeliveryService {

    List<DeliveryDto> findAll();
    DeliveryDto findById(Long id) throws ResourceNotFoundException;
    void delete(Long id);
    void saveAll(List<DeliveryDto> deliveryDtos) throws WarehouseException;
    List<DeliveryDto> findByDeliveryDateIsBetween(String first, String second);
    List<DeliveryDto> findByDeliveryDateGreaterThanEqual(String date);
    List<DeliveryDto> findByDeliveryDateLessThanEqual(String date);
    List<DeliveryDto> getByDate(String first, String last);
    DeliveryDto save(DeliveryDto deliveryDto) throws ResourceNotFoundException, WarehouseException;
    List<UniqueReportObject> getUniqueDeliveriesByRange(String first, String last);

}
