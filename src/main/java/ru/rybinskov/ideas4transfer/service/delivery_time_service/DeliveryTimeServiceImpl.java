package ru.rybinskov.ideas4transfer.service.delivery_time_service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.DeliveryTime;
import ru.rybinskov.ideas4transfer.domain.Warehouse;
import ru.rybinskov.ideas4transfer.dto.DeliveryTimeDto;
import ru.rybinskov.ideas4transfer.dto.WarehouseDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.DeliveryTimeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeliveryTimeServiceImpl implements DeliveryTimeService{

    private final DeliveryTimeRepository deliveryTimeRepository;

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
    public DeliveryTimeDto save(DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException, WarehouseException {
        if (deliveryTimeDto.getId() == null) {
            return new DeliveryTimeDto(deliveryTimeRepository.save(new DeliveryTime(deliveryTimeDto)));
        }
        DeliveryTime deliveryTime = deliveryTimeRepository.findById(deliveryTimeDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Время поставки с id = " + deliveryTimeDto.getId() + " не найдено"));
        deliveryTime.updateFields(deliveryTimeDto);
        return new DeliveryTimeDto(deliveryTimeRepository.save(deliveryTime));
    }

    @Override
    public void delete(Long id) {
        deliveryTimeRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<DeliveryTimeDto> deliveryTimeDto) {
        List<DeliveryTime> deliveryTime = deliveryTimeDto.stream().map(DeliveryTime::new).collect(Collectors.toList());
        deliveryTimeRepository.saveAll(deliveryTime);
    }
}