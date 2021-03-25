package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryTypeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.deleviry_type_service.DeliveryTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class DeliveryTypeController {

    private final DeliveryTypeService deliveryTypeService;

    public DeliveryTypeController(DeliveryTypeService deliveryTypeService) {
        this.deliveryTypeService = deliveryTypeService;
    }

    @GetMapping("/delivery-types")
    public List<DeliveryTypeDto> getAllDeliveryType() {
        return deliveryTypeService.findAll();
    }

    @GetMapping(value = "/delivery-types/{id}")
    public ResponseEntity<DeliveryTypeDto> getDeliveryTypeById(@PathVariable(value = "id") Long DeliveryTypeId)
            throws ResourceNotFoundException {
        DeliveryTypeDto deliveryTypeDto = deliveryTypeService.findById(DeliveryTypeId);
        return ResponseEntity.ok().body(deliveryTypeDto);
    }

    @PostMapping("/delivery-types")
    public void createDeliveryType(@RequestBody DeliveryTypeDto deliveryTypeDto) {
        deliveryTypeService.createDeliveryType(deliveryTypeDto);
    }

    @PutMapping("/delivery-types")
    public ResponseEntity<DeliveryTypeDto> updateDeliveryType(@RequestBody DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException {
        deliveryTypeService.updateDeliveryType(deliveryTypeDto);
        return ResponseEntity.ok(deliveryTypeDto);
    }

    @DeleteMapping("/delivery-types")
    public Map<String, Boolean> deleteDeliveryType(@RequestBody DeliveryTypeDto deliveryTypeDto) {
        deliveryTypeService.delete(deliveryTypeDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/grouped-delivery-types")
    public void saveAllDeliveryTypes(@RequestBody List<DeliveryTypeDto> deliveryTypeDto) {
        deliveryTypeService.saveAll(deliveryTypeDto);
    }
}
