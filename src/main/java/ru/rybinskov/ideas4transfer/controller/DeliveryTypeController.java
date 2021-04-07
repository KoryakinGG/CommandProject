package ru.rybinskov.ideas4transfer.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryTimeDto;
import ru.rybinskov.ideas4transfer.dto.DeliveryTypeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.service.deleviry_type_service.DeliveryTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
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
    public ResponseEntity<DeliveryTypeDto> addNewDeliveryType(@RequestBody DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException, WarehouseException {
        deliveryTypeDto.setId(null);
        return ResponseEntity.ok(deliveryTypeService.save(deliveryTypeDto));
    }

    @PutMapping("/delivery-types/{id}")
    public ResponseEntity<DeliveryTypeDto> updateDeliveryType(@PathVariable(value = "id") Long id, @RequestBody DeliveryTypeDto deliveryTypeDto) throws ResourceNotFoundException, WarehouseException {
        deliveryTypeDto.setId(id);
        deliveryTypeService.save(deliveryTypeDto);
        return ResponseEntity.ok(deliveryTypeDto);
    }

    @DeleteMapping("/delivery-types/{id}")
    public ResponseEntity<String> deleteDeliveryType(@PathVariable(value = "id") Long id) {
        deliveryTypeService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-delivery-types")
    public void saveAllDeliveryTypes(@RequestBody List<DeliveryTypeDto> deliveryTypeDto) {
        deliveryTypeService.saveAll(deliveryTypeDto);
    }
}
