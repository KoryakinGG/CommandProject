package ru.rybinskov.ideas4transfer.service.user_service;

import ru.rybinskov.ideas4transfer.dto.UserDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;


import java.util.List;

public interface UserService {
    UserDto getById(Long id) throws ResourceNotFoundException;
    List<UserDto> getAll();
    UserDto findByName(String name);
    void save(UserDto user);
    void delete(UserDto userDto);
}
