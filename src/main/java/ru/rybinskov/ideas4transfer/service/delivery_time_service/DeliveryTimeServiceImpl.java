package ru.rybinskov.ideas4transfer.service.delivery_time_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.DeliveryTime;
import ru.rybinskov.ideas4transfer.dto.DeliveryTimeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.DeliveryTimeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryTimeServiceImpl implements DeliveryTimeService{

    DeliveryTimeRepository deliveryTimeRepository;

    @Autowired
    public DeliveryTimeServiceImpl(DeliveryTimeRepository deliveryTimeRepository) {
        this.deliveryTimeRepository = deliveryTimeRepository;
    }

    @Override
    public List<DeliveryTimeDto> findAll() {
        return deliveryTimeRepository.findAll().stream().map(DeliveryTimeDto::new).collect(Collectors.toList());
    }

    @Override
    public DeliveryTimeDto findById(Long id) throws ResourceNotFoundException {
        DeliveryTime deliveryTime = deliveryTimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Дата по указанному id не найдена:  id = " + id));
        return new DeliveryTimeDto(deliveryTime);
    }

    @Override
    public void createDeliveryTime(DeliveryTimeDto deliveryTimeDto) {
        deliveryTimeRepository.save(new DeliveryTime(deliveryTimeDto));
    }

    @Override
    public void updateDeliveryTime(DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException {
        DeliveryTimeDto deliveryTime = findById(deliveryTimeDto.getId());
        deliveryTime.updateAllFieldsWithoutId(deliveryTimeDto);
        deliveryTimeRepository.save(new DeliveryTime(deliveryTime));
    }

    @Override
    public void delete(DeliveryTimeDto deliveryTimeDto) {
        deliveryTimeRepository.delete(new DeliveryTime(deliveryTimeDto));
    }

    @Override
    public void saveAll(List<DeliveryTimeDto> deliveryTimeDto) {
        List<DeliveryTime> deliveryTime = deliveryTimeDto.stream().map(DeliveryTime::new).collect(Collectors.toList());
        deliveryTimeRepository.saveAll(deliveryTime);
    }
}
