package ru.rybinskov.ideas4transfer.controller;

import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dto.UserDto;
import ru.rybinskov.ideas4transfer.service.user_service.UserService;

import java.util.List;

@CrossOrigin({"https://mywarehouseapp.herokuapp.com", "http://mywarehouseapp.herokuapp.com"})
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String addNewUser(@RequestBody UserDto user) {
        userService.save(user);
        return "Well Done";
    }

    @GetMapping("/users")
    public List<UserDto> getList() {return userService.getAll();}
}
