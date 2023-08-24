package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.SimpleUserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final SimpleUserService simpleUserService;

    public UserController(SimpleUserService simpleUserService) {
        this.simpleUserService = simpleUserService;
    }

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "users/register";
    }

    @PostMapping("/register")
    public String create(@ModelAttribute User user, Model model) {
        try {
            simpleUserService.save(user);
            return "redirect:/users";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }
}
