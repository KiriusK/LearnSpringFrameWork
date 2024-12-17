package ru.gb.homework4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework4.models.User;
import ru.gb.homework4.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/all")
    public String allUser(Model model) {
        model.addAttribute("users", service.getAllUser());
        return "allUser";
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user, Model model) {
        service.addUser(user);
        model.addAttribute("users", service.getAllUser());
        return "allUser";
    }

    @ExceptionHandler
    public String errorHnd(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

}
