package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.kneu.majnoreg.entity.User;
import ua.kneu.majnoreg.service.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = {"/users", "/users/"})
public class UserController {
    private final UserService userService;

    @GetMapping(path = {"/register", "/register/"})
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());
        log.info("Sending user register form");
        return "user/register";
    }

    @PostMapping(path = {"/register", "/register/"})
    public String addNewUser(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:";
    }

    @GetMapping(path = {"", "/"})
    public String getAllUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user/readAll";
    }

    @GetMapping(path = {"/{id}/edit", "/{id}/edit/"})
    public String getUserById(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", userService.findAllRoles());
        return "user/update";
    }

    @PatchMapping(path = {"/{id}", "/{id}/"})
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        user.setId(id);
        userService.update(user);
        return "redirect:";
    }

    @DeleteMapping(path = {"/{id}", "/{id}/"})
    public String deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return "redirect:";
    }
}
