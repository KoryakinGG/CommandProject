package ru.rybinskov.ideas4transfer.service.delivery_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.*;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.BrandRepository;
import ru.rybinskov.ideas4transfer.repository.DeliveryRepository;
import ru.rybinskov.ideas4transfer.repository.ShopRepository;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<DeliveryDto> findAll() {
        return deliveryRepository.findAll().stream().map(DeliveryDto::new).collect(Collectors.toList());
    }

    @Override
    public DeliveryDto findById(Long id) throws ResourceNotFoundException {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Поставка по указанному id не найдена:  id = " + id));
        return new DeliveryDto(delivery);

    }

    @Override
    public void updateDelivery(DeliveryDto deliveryDto) {
        deliveryRepository.save(new Delivery(deliveryDto));
    }

    @Override
    public void createDelivery(DeliveryDto deliveryDto) {
        deliveryRepository.save(new Delivery(deliveryDto));
    }

    public void delete(DeliveryDto deliveryDto) {

        deliveryRepository.delete(new Delivery(deliveryDto));
    }

}
