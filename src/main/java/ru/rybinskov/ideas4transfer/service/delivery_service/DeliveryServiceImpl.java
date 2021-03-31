package ru.rybinskov.ideas4transfer.service.delivery_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ExceedingAllowedDateValueException;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.DeliveryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public List<DeliveryDto> findAll() {
        log.info("Working method DeliveryService findAll");
        return deliveryRepository.findAll().stream().map(DeliveryDto::new).collect(Collectors.toList());
    }

    @Override
    public DeliveryDto findById(Long id) throws ResourceNotFoundException {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Поставка по указанному id не найдена:  id = " + id));
        log.info("Working method DeliveryService findById: {}", delivery);
        return new DeliveryDto(delivery);
    }

    @Override
    public DeliveryDto save(DeliveryDto deliveryDto) throws ResourceNotFoundException, WarehouseException {
        if (deliveryDto.getId() == null)
        {
            log.info("Working method DeliveryType save: {} is null, create new", deliveryDto.getId());
            return new DeliveryDto(deliveryRepository.save(new Delivery(deliveryDto)));
        }
        // обновление уже существующего пользователя
        Delivery delivery = deliveryRepository.findById(deliveryDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Поставка с id = " + deliveryDto.getId() + " не найдена"));

        //обновляем поля в user, не затрагивая пароля и Id
        delivery.updateFields(deliveryDto);
        log.info("Working method DeliveryService save: {}", delivery);
        return new DeliveryDto(deliveryRepository.save(delivery));
    }

    @Override
    public void delete(Long id) {
        deliveryRepository.deleteById(id);
        log.info("Working method DeliveryService delete: {} is deleted", id );
    }

    @Override
    public void saveAll(List<DeliveryDto> deliveryDtos) throws ExceedingAllowedDateValueException {
        List<DeliveryDto> listValidDeliveries = new ArrayList<>(), listNotValidDeliveries = new ArrayList<>();

        deliveryDtos.forEach(d -> {
            if (d.getDeliveryDate().
                    compareTo(LocalDate.now().plusDays(21)) <= 0) {
                listValidDeliveries.add(d);
            } else {
                listNotValidDeliveries.add(d);
            }

        });
        List<Delivery> deliveries = listValidDeliveries.stream().map(Delivery::new).collect(Collectors.toList());
        deliveryRepository.saveAll(deliveries);
        log.info("Working method DeliveryService saveAll: {}", deliveries );
        if (!listNotValidDeliveries.isEmpty()) {
            String notValidDeliveries = listNotValidDeliveries.stream()
                    .map(deliveryDto -> deliveryDto.getDeliveryDate().toString())
                    .collect(Collectors.joining("; ", "[ ", " ]"));
            throw new ExceedingAllowedDateValueException("На текущую дату невозможно оформить доставку. Ближайшая из возможных дат: "
                    + LocalDate.now().plusDays(21) + " или раньше. Исправьте даты поставок: " + notValidDeliveries);
        }
    }

    private DateTimeFormatter getFormatter() {
        String europeanDatePattern = "yyyy-MM-dd";
        return DateTimeFormatter.ofPattern(europeanDatePattern);
    }

    @Override
    public List<DeliveryDto> findByDeliveryDateIsBetween(String first, String last) {
        LocalDate firstDate = LocalDate.parse(first, getFormatter());
        LocalDate lastDate = LocalDate.parse(last, getFormatter());
        log.info("Working method DeliveryService findByDeliveryDateIsBetween: firstDate {}, LastDate {}", firstDate,lastDate);
        return deliveryRepository.findByDeliveryDateIsBetween(firstDate, lastDate)
                .stream().map(DeliveryDto::new)
                .sorted(Comparator.comparing(DeliveryDto::getDeliveryDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryDto> findByDeliveryDateGreaterThanEqual(String date) {
        LocalDate localDate = LocalDate.parse(date, getFormatter());
        log.info("Working method DeliveryService findByDeliveryDateGreaterThanEqual: {}", localDate);
        return deliveryRepository.findByDeliveryDateGreaterThanEqual(localDate)
                .stream().map(DeliveryDto::new)
                .sorted(Comparator.comparing(DeliveryDto::getDeliveryDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryDto> findByDeliveryDateLessThanEqual(String date) {
        LocalDate localDate = LocalDate.parse(date, getFormatter());
        log.info("Working method DeliveryService findByDeliveryDateLessThanEqual: {}", localDate);
        return deliveryRepository.findByDeliveryDateLessThanEqual(localDate)
                .stream().map(DeliveryDto::new)
                .sorted(Comparator.comparing(DeliveryDto::getDeliveryDate))
                .collect(Collectors.toList());
    }

    public List<DeliveryDto> getByDate(String first, String last){
        LocalDate firstDate = LocalDate.parse(first, getFormatter());
        LocalDate lastDate = LocalDate.parse(first, getFormatter());
        List<DeliveryDto> list = findAll();
        log.info("Working method DeliveryService getByDate: {}", list);
        return list.stream()
                .filter(delivery -> delivery.getDeliveryDate()
                .compareTo(firstDate) >= 0 && delivery.getDeliveryDate()
                .compareTo(lastDate) <= 0)
                .sorted(Comparator.comparing(DeliveryDto::getDeliveryDate))
                .collect(Collectors.toList());
    }
}
