package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.UserDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.service.user_service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        UserDto userDto = userService.getById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getList() {
        return userService.findAll();
    }

    // создание пользователя
    @PostMapping("/users")
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) throws ResourceNotFoundException, WarehouseException {
        userDto.setId(null);
        return ResponseEntity.ok(userService.save(userDto));
    }

    // изменение пользователя
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserDto userDto)
            throws ResourceNotFoundException, WarehouseException {
        userDto.setId(userId);  // игнорим, то что пришло в DTO
        return ResponseEntity.ok(userService.save(userDto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        userService.delete(userId);
        return ResponseEntity.ok("Deleted");
    }

}

