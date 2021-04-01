package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.dto.UserDto;
import ru.rybinskov.ideas4transfer.exception.ExceedingAllowedDateValueException;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.service.delivery_service.DeliveryService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping(value = "/deliveries/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable(value = "id") Long deliveryId)
            throws ResourceNotFoundException {
        DeliveryDto delivery = deliveryService.findById(deliveryId);
        return ResponseEntity.ok().body(delivery);
    }

    @PostMapping("/deliveries")
    public ResponseEntity<DeliveryDto> addNewDelivery(@RequestBody DeliveryDto deliveryDto) throws ExceedingAllowedDateValueException, ResourceNotFoundException, WarehouseException {
        deliveryDto.setId(null);
        return ResponseEntity.ok(deliveryService.save(deliveryDto));
    }

    @PutMapping("/deliveries/{id}")
    public ResponseEntity<DeliveryDto> updateDelivery(@PathVariable(value = "id") Long deliveryId, @RequestBody DeliveryDto deliveryDto) throws ResourceNotFoundException, ExceedingAllowedDateValueException, WarehouseException {
        deliveryDto.setId(deliveryId);
        deliveryService.save(deliveryDto);
        return ResponseEntity.ok(deliveryDto);
    }

    @DeleteMapping("/deliveries/{id}")
    public ResponseEntity<String> deleteDelivery(@PathVariable(value = "id") Long deliveryId) throws ResourceNotFoundException {
        deliveryService.delete(deliveryId);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-deliveries")
    public void saveAllDeliveries(@RequestBody List<DeliveryDto> deliveries) throws ExceedingAllowedDateValueException {
        deliveryService.saveAll(deliveries);
    }

    // http://localhost:8189/api/v1/deliveries?first=2021-04-23&last=2021-12-25
    @GetMapping(value = "/deliveries")
    public ResponseEntity<List<DeliveryDto>> filterByDate(@RequestParam(required = false, name = "first") String first,
                               @RequestParam(required = false, name = "last") String last){
        List<DeliveryDto> lists = new ArrayList<>();
        if (first == null && last == null) {
            lists =deliveryService.findAll();
        }
        else if (first != null && last == null) {
            lists = deliveryService.findByDeliveryDateGreaterThanEqual(first);
        }
        else if (first == null && last != null) {
            lists = deliveryService.findByDeliveryDateLessThanEqual(last);
        }
        else if (first != null && last != null) {
            lists = deliveryService.findByDeliveryDateIsBetween(first, last);
        }
        return ResponseEntity.ok().body(lists);
    }


    @GetMapping("/deliveries/new")
    public String createDelivery() throws ExceedingAllowedDateValueException, ResourceNotFoundException, WarehouseException {
        List<DeliveryDto> deliveryDtoList = deliveryService.findAll();
        DeliveryDto deliveryDto = deliveryDtoList.get(deliveryDtoList.size() - 1);
        deliveryDto.setId(null);
        deliveryDto.setDeliveryDate(deliveryDto.getDeliveryDate().plusDays(1L));
        deliveryService.save(deliveryDto);
        return "Congratulation, delivery created";
    }


}
