package ru.rybinskov.ideas4transfer.service.deleviry_type_service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.DeliveryTime;
import ru.rybinskov.ideas4transfer.domain.DeliveryType;
import ru.rybinskov.ideas4transfer.dto.DeliveryTimeDto;
import ru.rybinskov.ideas4transfer.dto.DeliveryTypeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.DeliveryTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeliveryTypeServiceImpl implements DeliveryTypeService{

    private final DeliveryTypeRepository deliveryTypeRepository;

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
    public DeliveryTypeDto save(DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException, WarehouseException {
        if (deliveryTypeDto.getId() == null) {
            return new DeliveryTypeDto(deliveryTypeRepository.save(new DeliveryType(deliveryTypeDto)));
        }
        DeliveryType deliveryType = deliveryTypeRepository.findById(deliveryTypeDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Тип поставки с id = " + deliveryTypeDto.getId() + " не найден"));
        deliveryType.updateFields(deliveryTypeDto);
        return new DeliveryTypeDto(deliveryTypeRepository.save(deliveryType));
    }

    @Override
    public void delete(Long id) {
        deliveryTypeRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<DeliveryTypeDto> deliveryTypeDto) {
        List<DeliveryType> deliveryTypes = deliveryTypeDto.stream().map(DeliveryType::new).collect(Collectors.toList());
        deliveryTypeRepository.saveAll(deliveryTypes);
    }
}
