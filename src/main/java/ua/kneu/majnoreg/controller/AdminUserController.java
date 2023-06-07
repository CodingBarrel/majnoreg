package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kneu.majnoreg.entity.UserInformation;
import ua.kneu.majnoreg.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin-users")
public class AdminUserController {
    private final UserService userService;

    @GetMapping(path = "")
    public String getAllUsers(Model model) {
        log.info("Sending all users");
        List<UserInformation> users = userService.findAll();
        model.addAttribute("userList", users);
        return "admin-user/readAll";
    }

    @GetMapping(path = "/{id}/edit")
    public String getUserById(@PathVariable int id, Model model) {
        log.info("Sending user {} for update", id);
        model.addAttribute("userInformation", userService.findById(id));
        model.addAttribute("roles", userService.findAllRoles());
        return "admin-user/update";
    }

    @PatchMapping(path = "/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute UserInformation userInformation) {
        log.info("Updating user {}", id);
        userInformation.setId(id);
        userService.update(userInformation);
        return "redirect:/admin-users";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable int id) {
        log.info("Deleting user {}", id);
        userService.deleteById(id);
        return "redirect:/admin-users";
    }
}
