package ru.rybinskov.ideas4transfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rybinskov.ideas4transfer.domain.User;
import ru.rybinskov.ideas4transfer.service.user_service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new-user")
    public String addNewUser(User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String getNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user-list";
    }
}
