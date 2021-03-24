package ru.rybinskov.ideas4transfer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.domain.Delivery;
import ru.rybinskov.ideas4transfer.domain.DeliveryTime;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.delivery_service.DeliveryService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
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
    public void createDelivery(@RequestBody DeliveryDto delivery) {
        deliveryService.createDelivery(delivery);
    }

    @PutMapping("/deliveries")
    public ResponseEntity<DeliveryDto> updateDelivery(@RequestBody DeliveryDto deliveryDetails) throws ResourceNotFoundException {
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
    public String createDelivery() throws JsonProcessingException {
        List<DeliveryDto> deliveryDtoList = deliveryService.findAll();
        DeliveryDto deliveryDto = deliveryDtoList.get(deliveryDtoList.size() -1);
        deliveryDto.setId(null);
        deliveryDto.setDeliveryDate(deliveryDto.getDeliveryDate().plusDays(1L));
        deliveryService.createDelivery(deliveryDto);
        return "Congratulation, delivery created";
    }

    @PostMapping("/grouped-deliveries")
    public void saveAllDeliveries(@RequestBody List<DeliveryDto> deliveries) {
        deliveryService.saveAll(deliveries);
    }

}
