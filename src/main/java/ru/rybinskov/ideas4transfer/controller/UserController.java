package ru.rybinskov.ideas4transfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.UserDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.service.user_service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin({"http://localhost:4200","https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
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
    public List<UserDto> getList() {return userService.getAll();}

    @PostMapping("/users")
    public String addNewUser(@RequestBody UserDto user) {
        userService.save(user);
        return "Well Done";
    }

    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDetails) throws ResourceNotFoundException {
        UserDto userDto = userService.getById(userDetails.getId());
        userDto.updateAllFieldsWithoutId(userDetails);
        userService.save(userDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/users")
    public Map<String, Boolean> deleteUser(@RequestBody UserDto userDto) {
        userService.delete(userDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
