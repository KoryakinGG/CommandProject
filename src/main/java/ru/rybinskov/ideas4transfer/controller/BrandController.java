package ru.rybinskov.ideas4transfer.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ExceedingAllowedDateValueException;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.service.brand_service.BrandService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
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
    public ResponseEntity<BrandDto> addNewBrand(@RequestBody BrandDto brandDto) throws ResourceNotFoundException, WarehouseException {
        brandDto.setId(null);
        return ResponseEntity.ok(brandService.save(brandDto));
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<BrandDto> updateBrand(@PathVariable(value = "id") Long id, @RequestBody BrandDto brandDto) throws ResourceNotFoundException, WarehouseException {
        brandDto.setId(id);
        brandService.save(brandDto);
        return ResponseEntity.ok(brandDto);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable(value = "id") Long id) {
        brandService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/grouped-brands")
    public void saveAllBrands(@RequestBody List<BrandDto> brandDtos) {
        brandService.saveAll(brandDtos);
    }

}
