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

        ObjectMapper mapper = new ObjectMapper();
        String json =
                "{\n" +
                        "  \"id\" : 1,\n" +
                        "  \"deliveryDate\" : \"10.03.2021\",\n" +
                        "  \"deliveryTime\" : {\n" +
                        "    \"id\" : 1,\n" +
                        "    \"deliveryTime\" : \"23.03.2021\"\n" +
                        "  },\n" +
                        "  \"carInfo\" : \"Н496ХВ197\",\n" +
                        "  \"driverInfo\" : \"Vasya\",\n" +
                        "  \"brand\" : {\n" +
                        "    \"id\" : 1,\n" +
                        "    \"name\" : \"Goods House\",\n" +
                        "    \"abbr\" : \"GDH\"\n" +
                        "  },\n" +
                        "  \"orderNumber\" : \"w12344\",\n" +
                        "  \"deliveryType\" : {\n" +
                        "    \"id\" : 1,\n" +
                        "    \"type\" : \"CROSS_DOCKING\"\n" +
                        "  },\n" +
                        "  \"sender\" : \"ООО Какая-то компания\",\n" +
                        "  \"comment\" : \"доставить вовремя\",\n" +
                        "  \"shop\" : {\n" +
                        "    \"id\" : 1,\n" +
                        "    \"name\" : \"Goods House Авеню\",\n" +
                        "    \"abbr\" : \"GDH1\",\n" +
                        "    \"brand\" : {\n" +
                        "      \"id\" : 1,\n" +
                        "      \"name\" : \"Goods House 2\",\n" +
                        "      \"abbr\" : \"GDH2\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"numberOfPlaces\" : \"40\",\n" +
                        "  \"torgNumber\" : \"1123\",\n" +
                        "  \"invoice\" : \"1123\",\n" +
                        "  \"user\" : {\n" +
                        "    \"id\" : 1,\n" +
                        "    \"username\" : \"Gogi\",\n" +
                        "    \"roles\" : [ {\n" +
                        "      \"id\" : 1,\n" +
                        "      \"role\" : \"ROLE_ADMIN\"\n" +
                        "    } ],\n" +
                        "    \"fullName\" : \"Gogi Gogi\",\n" +
                        "    \"email\" : \"gogi@gmail.com\",\n" +
                        "    \"phone\" : \"88002222222\",\n" +
                        "    \"password\" : \"password\",\n" +
                        "    \"brands\" : [ {\n" +
                        "      \"id\" : 1,\n" +
                        "      \"name\" : \"Goods House\",\n" +
                        "      \"abbr\" : \"GDH\"\n" +
                        "    } ]\n" +
                        "  },\n" +
                        "  \"warehouse\" : {\n" +
                        "    \"id\" : 1,\n" +
                        "    \"name\" : \"Склад №1\",\n" +
                        "    \"abbr\" : \"skd1\"\n" +
                        "  }\n" +
                        "}";
        DeliveryDto deliveryDto = mapper.readValue(json, DeliveryDto.class);
        deliveryService.createDelivery(deliveryDto);
        return "Congratulation";
    }
}
