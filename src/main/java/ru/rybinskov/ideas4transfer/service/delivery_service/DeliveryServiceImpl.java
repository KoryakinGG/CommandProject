package ru.rybinskov.ideas4transfer.service.delivery_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.DeliveryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;

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
    public void updateDelivery(DeliveryDto deliveryDto) throws ResourceNotFoundException {
        DeliveryDto delivery = findById(deliveryDto.getId());
        delivery.updateAllFieldsWithoutId(deliveryDto);
        deliveryRepository.save(new Delivery(delivery));
    }

    @Override
    public void createDelivery(DeliveryDto deliveryDto) {
        deliveryRepository.save(new Delivery(deliveryDto));
    }

    public void delete(DeliveryDto deliveryDto) {
        deliveryRepository.delete(new Delivery(deliveryDto));
    }

    @Override
    public void saveAll(List<DeliveryDto> deliveryDtos) {
        List<Delivery> deliveries = deliveryDtos.stream().map(Delivery::new).collect(Collectors.toList());
        deliveryRepository.saveAll(deliveries);
    }

    @Override
    public List<DeliveryDto> findByDeliveryDateIsBetween(String first, String last) {
        String europeanDatePattern = "yyyy-MM-dd";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        LocalDate firstDate = LocalDate.parse(first, europeanDateFormatter);
        LocalDate lastDate = LocalDate.parse(last, europeanDateFormatter);
        List<DeliveryDto> lists = deliveryRepository.findByDeliveryDateIsBetween(firstDate, lastDate).stream().map(DeliveryDto::new).collect(Collectors.toList());
        lists.sort(Comparator.comparing(DeliveryDto::getDeliveryDate));
        return lists;
    }
}
