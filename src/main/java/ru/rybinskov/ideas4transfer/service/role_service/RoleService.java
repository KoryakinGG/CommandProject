package ru.rybinskov.ideas4transfer.service.role_service;

import ru.rybinskov.ideas4transfer.dto.RoleDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();
    RoleDto findById(Long id) throws ResourceNotFoundException;
    void createRole(RoleDto roleDto);
    void updateRole(RoleDto roleDto) throws ResourceNotFoundException;
    void delete(RoleDto roleDto);
    void saveAll(List<RoleDto> roleDto);
}
