package ru.rybinskov.ideas4transfer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.delivery_service.DeliveryService;

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
        DeliveryDto delivery = deliveryService.findById(deliveryDetails.getId());
        delivery.updateAllFieldsWithoutId(deliveryDetails);
        deliveryService.updateDelivery(delivery);
        return ResponseEntity.ok(delivery);
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

        ObjectMapper mapper = new ObjectMapper();
        String json =
                "{\n" +
                        "        \"deliveryDate\": \"09.03.2021\",\n" +
                        "        \"deliveryTime\": \"\",\n" +
                        "        \"carInfo\": \"Reno Master Н496ХВ197\",\n" +
                        "        \"driverInfo\": \"Vasya Terkin\",\n" +
                        "        \"brand\": \"j\",\n" +  // BrandDto
                        "        \"orderNumber\": \"GDH01000011-2021\",\n" +
                        "        \"deliveryType\": \"кросс-докинг\",\n" + // LONG
                        "        \"sender\": \"ООО Какой-то магазин\",\n" +
                        "        \"comment\": \"ОПАЗДЫВАТЬ НЕЛЬЗЯ\",\n" +
                        "        \"shop\": \"Goods House Авеню\",\n" +  // ShopDto
                        "        \"numberOfPlaces\": \"20\",\n" +
                        "        \"torgNumber\": \"QWERTY123\",\n" +
                        "        \"invoice\": \"QWERTY123\",\n" +
                        "        \"user\": \"Alex\",\n" +  // UserDto
                        "        \"warehouse\": \"Склад №1\"\n" + // WarehouseDto
                        "    }";
        DeliveryDto deliveryDto = mapper.readValue(json, DeliveryDto.class);
        deliveryService.createDelivery(deliveryDto);
        return "Congratulation";
    }
}
