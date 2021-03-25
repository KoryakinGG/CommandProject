package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.DeliveryTypeDto;
import ru.rybinskov.ideas4transfer.dto.RoleDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.deleviry_type_service.DeliveryTypeService;
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
    public void createRoleType(@RequestBody RoleDto roleDto) {
        roleService.createRole(roleDto);
    }

    @PutMapping("/roles")
    public ResponseEntity<RoleDto> updateRoleType(@RequestBody RoleDto roleDto) throws ResourceNotFoundException {
        roleService.updateRole(roleDto);
        return ResponseEntity.ok(roleDto);
    }

    @DeleteMapping("/roles")
    public Map<String, Boolean> deleteRoleType(@RequestBody RoleDto roleDto) {
        roleService.delete(roleDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/grouped-roles")
    public void saveAllRoleTypes(@RequestBody List<RoleDto> roleDto) {
        roleService.saveAll(roleDto);
    }
}
