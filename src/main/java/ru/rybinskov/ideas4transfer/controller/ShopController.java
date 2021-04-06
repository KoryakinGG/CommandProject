package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.service.shop_service.ShopService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<ShopDto> addNewBrand(@RequestBody ShopDto shopDto) throws ResourceNotFoundException, WarehouseException {
        shopDto.setId(null);
        return ResponseEntity.ok(shopService.save(shopDto));
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<ShopDto> updateBrand(@PathVariable(value = "id") Long id, @RequestBody ShopDto shopDto) throws ResourceNotFoundException, WarehouseException {
        shopDto.setId(id);
        shopService.save(shopDto);
        return ResponseEntity.ok(shopDto);
    }

    @DeleteMapping("/shops/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable(value = "id") Long id) {
        shopService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-shops")
    public void saveAllShops(@RequestBody List<ShopDto> shopDtos) {
        shopService.saveAll(shopDtos);
    }

}
