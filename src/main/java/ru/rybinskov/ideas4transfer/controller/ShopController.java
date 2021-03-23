package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.shop_service.ShopService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {this.shopService = shopService;}

    @GetMapping("/shops")
    public List<ShopDto> getAllShop() {return shopService.findAll();}

    @GetMapping(value = "/shops/{id}")
    public ResponseEntity<ShopDto> getShopById(@PathVariable(value = "id") Long shopId) throws ResourceNotFoundException {
        ShopDto shopDto = shopService.findById(shopId);
        return ResponseEntity.ok().body(shopDto);
    }

    @PostMapping("/shops")
    public void createShop(@RequestBody ShopDto shopDto) {shopService.save(shopDto);}

    @PutMapping("/shops")
    public ResponseEntity<ShopDto> updateShop(@RequestBody ShopDto shopDto) throws ResourceNotFoundException {
        shopService.updateShop(shopDto);
        return ResponseEntity.ok(shopDto);
    }

    @DeleteMapping("/shops")
    public Map<String, Boolean> deleteShop(@RequestBody ShopDto shopDto) {
        shopService.delete(shopDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
