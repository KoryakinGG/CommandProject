package ru.rybinskov.ideas4transfer.service.role_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.Role;
import ru.rybinskov.ideas4transfer.dto.RoleDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(RoleDto::new).collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) throws ResourceNotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Роль по указанному id не найдена:  id = " + id));;
        return new RoleDto(role);
    }

    @Override
    public void createRole(RoleDto roleDto) {
        roleRepository.save(new Role(roleDto));
    }

    @Override
    public void updateRole(RoleDto roleDto) throws ResourceNotFoundException {
        roleRepository.delete(new Role(roleDto));
    }

    @Override
    public void delete(RoleDto roleDto) {
        roleRepository.delete(new Role(roleDto));
    }

    @Override
    public void saveAll(List<RoleDto> roleDto) {
        List<Role> roles = roleDto.stream().map(Role::new).collect(Collectors.toList());
        roleRepository.saveAll(roles);
    }
}
