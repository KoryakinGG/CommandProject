package ru.rybinskov.ideas4transfer.service.deleviry_type_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.DeliveryType;
import ru.rybinskov.ideas4transfer.dto.DeliveryTypeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.DeliveryTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService{

    DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    public DeliveryTypeServiceImpl(DeliveryTypeRepository deliveryTypeRepository) {
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @Override
    public List<DeliveryTypeDto> findAll() {
        return deliveryTypeRepository.findAll().stream().map(DeliveryTypeDto::new).collect(Collectors.toList());
    }

    @Override
    public DeliveryTypeDto findById(Long id) throws ResourceNotFoundException {
        DeliveryType deliveryType = deliveryTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Дата по указанному id не найдена:  id = " + id));
        return new DeliveryTypeDto(deliveryType);
    }

    @Override
    public void createDeliveryType(DeliveryTypeDto deliveryTypeDto) {
        deliveryTypeRepository.save(new DeliveryType(deliveryTypeDto));
    }

    @Override
    public void updateDeliveryType(DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException {
        DeliveryTypeDto deliveryType = findById(deliveryTypeDto.getId());
        deliveryType.updateAllFieldsWithoutId(deliveryTypeDto);
        deliveryTypeRepository.save(new DeliveryType(deliveryType));
    }

    @Override
    public void delete(DeliveryTypeDto deliveryTypeDto) {
        deliveryTypeRepository.delete(new DeliveryType(deliveryTypeDto));
    }

    @Override
    public void saveAll(List<DeliveryTypeDto> deliveryTypeDto) {
        List<DeliveryType> deliveryTypes = deliveryTypeDto.stream().map(DeliveryType::new).collect(Collectors.toList());
        deliveryTypeRepository.saveAll(deliveryTypes);
    }
}
