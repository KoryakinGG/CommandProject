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

@CrossOrigin({"http://localhost:4200", "https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable(value = "id") Long usersId) throws ResourceNotFoundException {
        UserDto userDto = userService.getById(usersId);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getList() {
        return userService.findAll();
    }

//    @PostMapping("/users")
//    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
//        userDto.setId(null);
//        return ResponseEntity.ok(userService.save(userDto));
//    }

    // создание или изменение пользователя
    @PostMapping("/users")
    public ResponseEntity<UserDto> addOrUpdateUser(@RequestBody UserDto userDto)
            throws ResourceNotFoundException, WarehouseException {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @DeleteMapping("/users")
    public String deleteUser(@RequestBody UserDto userDto) {
        userService.delete(userDto);
        return "Deleted";
    }

}
