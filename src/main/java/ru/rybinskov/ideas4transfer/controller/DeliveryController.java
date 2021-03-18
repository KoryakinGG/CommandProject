package ru.rybinskov.ideas4transfer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryJson;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.delivery_service.DeliveryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // http://localhost:8189/command-project/api/v1/deliveries
    @GetMapping("/deliveries")
    public List<DeliveryJson> getAllDeliveries() {
        return deliveryService.findAll();
    }

    // http://localhost:8189/command-project/api/v1/deliveries/{id}
    @GetMapping(value = "/deliveries/{id}")
    public ResponseEntity<DeliveryJson> getDeliveryById(@PathVariable(value = "id") Long deliveryId)
            throws ResourceNotFoundException {
        DeliveryJson delivery = deliveryService.findById(deliveryId);
        return ResponseEntity.ok().body(delivery);
    }

    // http://localhost:8189/command-project/api/v1/deliveries/new
    @PostMapping("/deliveries/new")
    public void createDelivery(@RequestBody DeliveryJson delivery) throws JsonProcessingException {
        deliveryService.createDelivery(delivery);
    }

    // http://localhost:8189/command-project/api/v1/deliveries/{id}
    @PutMapping("/deliveries") //update по ID
    public ResponseEntity<DeliveryJson> updateDelivery(@RequestBody DeliveryJson deliveryDetails) throws ResourceNotFoundException, JsonProcessingException {
        DeliveryJson delivery = deliveryService.findByJson(deliveryDetails);
        delivery.updateAllFieldsWithoutId(deliveryDetails);
        deliveryService.updateDelivery(delivery);
        return ResponseEntity.ok(delivery);
    }

    // http://localhost:8189/command-project/api/v1/deliveries/
    @DeleteMapping("/deliveries") // добавить метод гет с перадресацией на delete
    public Map<String, Boolean> deleteDelivery(@RequestBody DeliveryJson delivery) {
        deliveryService.delete(delivery);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


////    // метод для тестов
//    // http://localhost:8189/command-project/api/v1/deliveries/new
    @GetMapping("/deliveries/new")
    public String createDelivery() throws JsonProcessingException { // @RequestBody DeliveryJson delivery

        ObjectMapper mapper = new ObjectMapper();
        String json =
                "  {\n" +
                        "    \"deliveryDate\": \"09.03.2021\",\n" +
                        "    \"deliveryTime\": \"первая половина дня\",\n" +
                        "    \"carInfo\": \"Reno Master Н496ХВ197\",\n" +
                        "    \"driverInfo\": \"Обломов Василий Олегович 89257834209\",\n" +
                        "    \"brand\": \"ckh\",\n" +
                        "    \"orderNumber\": \"GDH01000011-2021\",\n" +
                        "    \"deliveryType\": \"кросс-докинг\",\n" +
                        "    \"sender\": \"Goods House РИО Ленинский\",\n" +
                        "    \"comment\": \"Доставить вовремя\",\n" +
                        "    \"shop\": \"ООО Какой-то магазин\",\n" +
                        "    \"numberOfPlaces\": \"40\",\n" +
                        "    \"torgNumber\": \"q456\",\n" +
                        "    \"invoice\": \"invoice123\"\n" +
                        "  }\n";
        DeliveryJson deliveryJson = mapper.readValue(json,DeliveryJson.class);
        deliveryService.createDelivery(deliveryJson);
        return "Congratulation";
    }

}
