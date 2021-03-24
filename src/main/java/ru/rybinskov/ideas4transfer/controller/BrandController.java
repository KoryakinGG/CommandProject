package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.brand_service.BrandService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public List<BrandDto> getAllBrand() {
        return brandService.findAll();
    }

    @GetMapping(value = "/brands/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable(value = "id") Long brandId)
            throws ResourceNotFoundException {
        BrandDto brandDto = brandService.findById(brandId);
        return ResponseEntity.ok().body(brandDto);
    }

    @PostMapping("/brands")
    public void createBrand(@RequestBody BrandDto brandDto) {
        brandService.createBrand(brandDto);
    }

    @PutMapping("/brands")
    public ResponseEntity<BrandDto> updateBrand(@RequestBody BrandDto brandDetails) throws ResourceNotFoundException {
        brandService.updateBrand(brandDetails);
        return ResponseEntity.ok(brandDetails);
    }

    @DeleteMapping("/brands")
    public Map<String, Boolean> deleteBrand(@RequestBody BrandDto brandDto) {
        brandService.delete(brandDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/grouped-brands")
    public void saveAllBrands(@RequestBody List<BrandDto> brandDtos) {
        brandService.saveAll(brandDtos);
    }

}
