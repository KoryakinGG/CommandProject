package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.RoleDto;
import ru.rybinskov.ideas4transfer.dto.WarehouseDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.service.role_service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<RoleDto> getAllRoleType() {
        return roleService.findAll();
    }

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(value = "id") Long roleId)
            throws ResourceNotFoundException {
        RoleDto roleDto = roleService.findById(roleId);
        return ResponseEntity.ok().body(roleDto);
    }
    @PostMapping("/roles")
    public ResponseEntity<RoleDto> addNewRole(@RequestBody RoleDto roleDto) throws ResourceNotFoundException, WarehouseException {
        roleDto.setId(null);
        return ResponseEntity.ok(roleService.save(roleDto));
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable(value = "id") Long id, @RequestBody RoleDto roleDto) throws ResourceNotFoundException, WarehouseException {
        roleDto.setId(id);
        roleService.save(roleDto);
        return ResponseEntity.ok(roleDto);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable(value = "id") Long id) {
        roleService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
    @PostMapping("/grouped-roles")
    public void saveAllRoleTypes(@RequestBody List<RoleDto> roleDto) {
        roleService.saveAll(roleDto);
    }
}
