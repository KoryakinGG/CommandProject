package ru.rybinskov.ideas4transfer.service.role_service;

import ru.rybinskov.ideas4transfer.dto.RoleDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();
    RoleDto findById(Long id) throws ResourceNotFoundException;
    RoleDto save(RoleDto roleDto) throws ResourceNotFoundException, WarehouseException;
    void delete(Long id);
    void saveAll(List<RoleDto> roleDto);
}
