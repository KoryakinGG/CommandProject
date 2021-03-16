package ru.koryaking.ideas4transfer.service.delivery_service;

import org.json.simple.parser.ParseException;
import ru.koryaking.ideas4transfer.domain.Delivery;

import java.util.List;

public interface DeliveryService {

    Delivery getLandingById(Long id);
    Delivery save(Delivery delivery);
    List<Delivery> getAll();
    List<Delivery> getAllSimplifiedLandingByUser(String username);
    void deleteById(Long id);
    void saveList(List<Delivery> list);
    String saveJson(Delivery delivery) throws ParseException;
}
