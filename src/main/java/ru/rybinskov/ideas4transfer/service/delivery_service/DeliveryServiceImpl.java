package ru.rybinskov.ideas4transfer.service.delivery_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryJson;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.BrandRepository;
import ru.rybinskov.ideas4transfer.repository.DeliveryRepository;
import ru.rybinskov.ideas4transfer.repository.ShopRepository;

import java.util.*;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    private DeliveryRepository deliveryRepository;
    private BrandRepository brandRepository;
    private ShopRepository shopRepository;

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Autowired
    public void setDeliveryRepository(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }
//
//    @Override
//    public Delivery DeliveryById(Long id) {
//        return deliveryRepository.findDeliveryById(id);
//    }
//
//    @Override
//    public void save(Delivery delivery) {
//        deliveryRepository.save(delivery);
//    }
//
//    @Override
//    public List<Delivery> getAll() {
//        return deliveryRepository.findAll();
//    }

    @Override
    public List<DeliveryJson> findAll() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        List<DeliveryJson> deliveriesJsons =new ArrayList<>();
        Delivery delivery;
        DeliveryJson deliveryJson;

        for (Delivery value : deliveries) {
            delivery = value;
            deliveryJson = DeliveryJson.builder()
                    .id(delivery.getId())
                    .deliveryDate(delivery.getDeliveryDate())
                    .deliveryTime(delivery.getDeliveryTime().getName())
                    .carInfo(delivery.getCarInfo())
                    .driverInfo(delivery.getDriverInfo())
                    .brand(delivery.getBrand().getAbbr())
                    .orderNumber(delivery.getOrderNumber())
                    .deliveryType(delivery.getDeliveryType().getName())
                    .sender(delivery.getSender())
                    .comment(delivery.getComment())
                    .shop(delivery.getShop().getName())
                    .numberOfPlaces(delivery.getNumberOfPlaces())
                    .torgNumber(delivery.getTorgNumber())
                    .invoice(delivery.getInvoice())
                    .build();
            deliveriesJsons.add(deliveryJson);
        }
        deliveries = null;
        return deliveriesJsons;
    }

    @Override
    public DeliveryJson findById(Long id) throws ResourceNotFoundException {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Поставка по указанному id не найдена:  id = " + id));
        return new DeliveryJson(delivery.getId(), delivery.getDeliveryDate(), delivery.getDeliveryTime().getName(),
                delivery.getCarInfo(), delivery.getDriverInfo(),
                delivery.getBrand().getAbbr(), delivery.getOrderNumber(),
                delivery.getDeliveryType().getName(), delivery.getSender(),
                delivery.getComment(), delivery.getShop().getName(),
                delivery.getNumberOfPlaces(), delivery.getTorgNumber(), delivery.getInvoice());

    }

    @Override
    public DeliveryJson findByJson(DeliveryJson deliveryJson) throws ResourceNotFoundException {
        Long id = deliveryJson.getId();
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Поставка по указанному id не найдена:  id = " + id));
        return new DeliveryJson(delivery.getId(), delivery.getDeliveryDate(), delivery.getDeliveryTime().getName(),
                delivery.getCarInfo(), delivery.getDriverInfo(),
                delivery.getBrand().getAbbr(), delivery.getOrderNumber(),
                delivery.getDeliveryType().getName(), delivery.getSender(),
                delivery.getComment(), delivery.getShop().getName(),
                delivery.getNumberOfPlaces(), delivery.getTorgNumber(), delivery.getInvoice());
    }

    @Override
    public void updateDelivery(DeliveryJson deliveryJson) {
        Delivery delivery = getDelivery(deliveryJson);
        deliveryRepository.save(delivery);
    }

    @Override
    public void createDelivery(DeliveryJson deliveryJson) {
        Delivery delivery = getDelivery(deliveryJson);
        deliveryRepository.save(delivery);
    }

    public void delete(DeliveryJson deliveryJson) {
        Delivery delivery = getDelivery(deliveryJson);
        deliveryRepository.delete(delivery);
    }

    private Brand getBrand(String name) {
        return brandRepository.findBrandByAbbr(name);
    }

    private Shop getShop(String name) {
        return shopRepository.findShopsByName(name);
    }

    private Delivery getDelivery(DeliveryJson deliveryJson) {
        return Delivery.builder()
                .id(deliveryJson.getId())
                .deliveryDate(deliveryJson.getDeliveryDate())
                .deliveryTime(DeliveryTime.valueOfOrDefault(deliveryJson.getDeliveryTime()))
                .carInfo(deliveryJson.getCarInfo())
                .driverInfo(deliveryJson.getDriverInfo())
                .brand(getBrand(deliveryJson.getBrand()))
                .orderNumber(deliveryJson.getOrderNumber())
                .deliveryType(DeliveryType.valueOfOrDefault(deliveryJson.getDeliveryType()))
                .sender(deliveryJson.getSender())
                .comment(deliveryJson.getComment())
                .shop(getShop(deliveryJson.getShop()))
                .numberOfPlaces(deliveryJson.getNumberOfPlaces())
                .torgNumber(deliveryJson.getTorgNumber())
                .invoice(deliveryJson.getInvoice())
                .build();
    }

}
