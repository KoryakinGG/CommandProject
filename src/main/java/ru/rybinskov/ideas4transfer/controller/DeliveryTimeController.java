package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryTimeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.delivery_time_service.DeliveryTimeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class DeliveryTimeController {

    private final DeliveryTimeService deliveryTimeService;

    public DeliveryTimeController(DeliveryTimeService deliveryTimeService) {
        this.deliveryTimeService = deliveryTimeService;
    }

    @GetMapping("/delivery-times")
    public List<DeliveryTimeDto> getAllDeliveryTime() {
        return deliveryTimeService.findAll();
    }

    @GetMapping(value = "/delivery-times/{id}")
    public ResponseEntity<DeliveryTimeDto> getDeliveryTimeById(@PathVariable(value = "id") Long DeliveryTimeId)
            throws ResourceNotFoundException {
        DeliveryTimeDto deliveryTimeDto = deliveryTimeService.findById(DeliveryTimeId);
        return ResponseEntity.ok().body(deliveryTimeDto);
    }

    @PostMapping("/delivery-times")
    public void createDeliveryTime(@RequestBody DeliveryTimeDto deliveryTimeDto) {
        deliveryTimeService.createDeliveryTime(deliveryTimeDto);
    }

    @PutMapping("/delivery-times")
    public ResponseEntity<DeliveryTimeDto> updateDeliveryTime(@RequestBody DeliveryTimeDto deliveryTimeDto) throws ResourceNotFoundException {
        deliveryTimeService.updateDeliveryTime(deliveryTimeDto);
        return ResponseEntity.ok(deliveryTimeDto);
    }

    @DeleteMapping("/delivery-times")
    public Map<String, Boolean> deleteDeliveryTime(@RequestBody DeliveryTimeDto deliveryTimeDto) {
        deliveryTimeService.delete(deliveryTimeDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/grouped-delivery-times")
    public void saveAllDeliveryTimes(@RequestBody List<DeliveryTimeDto> deliveryTimeDto) {
        deliveryTimeService.saveAll(deliveryTimeDto);
    }
}
