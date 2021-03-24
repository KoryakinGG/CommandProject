package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.WarehouseDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.warehouse_service.WarehouseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {this.warehouseService = warehouseService;}

    @GetMapping("/warehouses")
    public List<WarehouseDto> getAllWarehouses() {return warehouseService.findAll();}

    @GetMapping(value = "/warehouses/{id}")
    public ResponseEntity<WarehouseDto> getWarehouseById(@PathVariable(value = "id") Long warehouseId) throws ResourceNotFoundException {
        WarehouseDto warehouseDto = warehouseService.findById(warehouseId);
        return ResponseEntity.ok().body(warehouseDto);
    }

    @PostMapping("/warehouses")
    public void createWarehouse(@RequestBody WarehouseDto warehouseDto) {warehouseService.save(warehouseDto);}

    @PutMapping("/warehouses")
    public ResponseEntity<WarehouseDto> updateWarehouse(@RequestBody WarehouseDto warehouseDto) throws ResourceNotFoundException {
        warehouseService.updateWarehouse(warehouseDto);
        return ResponseEntity.ok(warehouseDto);
    }

    @DeleteMapping("/warehouses")
    public Map<String, Boolean> deleteWarehouse(@RequestBody WarehouseDto warehouseDto) {
        warehouseService.delete(warehouseDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/grouped-warehouses")
    public void saveAllWarehouses(@RequestBody List<WarehouseDto> warehouseDtos) {
        warehouseService.saveAll(warehouseDtos);
    }
}
