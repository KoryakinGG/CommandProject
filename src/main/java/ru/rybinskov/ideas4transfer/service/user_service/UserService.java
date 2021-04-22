package ru.rybinskov.ideas4transfer.service.user_service;

import ru.rybinskov.ideas4transfer.dto.UserDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;

import java.util.List;

public interface UserService {
    UserDto getById(Long id) throws ResourceNotFoundException;
    List<UserDto> findAll();
    UserDto save(UserDto userDto) throws ResourceNotFoundException, WarehouseException;
    void delete(Long id) throws ResourceNotFoundException;
}
