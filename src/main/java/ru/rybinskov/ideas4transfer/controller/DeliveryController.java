package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ExceedingAllowedDateValueException;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.delivery_service.DeliveryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200", "https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/deliveries")
    public List<DeliveryDto> getAllDeliveries() {
        return deliveryService.findAll();
    }

    @GetMapping(value = "/deliveries/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable(value = "id") Long deliveryId)
            throws ResourceNotFoundException {
        DeliveryDto delivery = deliveryService.findById(deliveryId);
        return ResponseEntity.ok().body(delivery);
    }

    @PostMapping("/deliveries")
    public void createDelivery(@RequestBody DeliveryDto delivery) throws ExceedingAllowedDateValueException {
        deliveryService.createDelivery(delivery);
    }

    @PutMapping("/deliveries")
    public ResponseEntity<DeliveryDto> updateDelivery(@RequestBody DeliveryDto deliveryDetails) throws ResourceNotFoundException, ExceedingAllowedDateValueException {
        deliveryService.updateDelivery(deliveryDetails);
        return ResponseEntity.ok(deliveryDetails);
    }

    @DeleteMapping("/deliveries")
    public Map<String, Boolean> deleteDelivery(@RequestBody DeliveryDto delivery) {
        deliveryService.delete(delivery);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/deliveries/new")
    public String createDelivery() throws ExceedingAllowedDateValueException {
        List<DeliveryDto> deliveryDtoList = deliveryService.findAll();
        DeliveryDto deliveryDto = deliveryDtoList.get(deliveryDtoList.size() - 1);
        deliveryDto.setId(null);
        deliveryDto.setDeliveryDate(deliveryDto.getDeliveryDate().plusDays(1L));
        deliveryService.createDelivery(deliveryDto);
        return "Congratulation, delivery created";
    }

    @PostMapping("/grouped-deliveries")
    public void saveAllDeliveries(@RequestBody List<DeliveryDto> deliveries) throws ExceedingAllowedDateValueException {
        deliveryService.saveAll(deliveries);
    }

    // http://localhost:8189/api/v1/grouped-deliveries?first=2021-12-25&last=2021-12-25
    @GetMapping(value = "/grouped-deliveries", params = {"first", "last"})
    public ResponseEntity<List<DeliveryDto>> getByDeliveryDateIsBetween(String first, String last) {
        List<DeliveryDto> lists = deliveryService.findByDeliveryDateIsBetween(first, last);
        return ResponseEntity.ok().body(lists);
    }

}
