package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kneu.majnoreg.entity.User;
import ua.kneu.majnoreg.entity.UserCredentials;
import ua.kneu.majnoreg.entity.UserInformation;
import ua.kneu.majnoreg.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(path = "/register")
    public String getRegisterForm(Model model) {
        log.info("Sending user register form");
        model.addAttribute("user", new User());
        return "user/register";
    }


    @Transactional
    @PostMapping(path = "/register")
    public String addNewUser(@ModelAttribute User user) {
            log.info("Creating user info {}", user.getInformation());
            userService.create(user.getInformation());
            user.getCredentials().setUserInformation(user.getInformation());
            user.getCredentials().setPassword(passwordEncoder.encode(user.getCredentials().getPassword()));
            log.info("Creating user creds {}", user.getCredentials());
            userService.createCredentials(user.getCredentials());
        return "redirect:/";
    }

    @GetMapping(path = "/login")
    public String getLoginForm(Model model){
        log.info("Sending user login form");
        model.addAttribute("userCredentials", new UserCredentials());
        return "user/login";
    }

    @GetMapping(path = "")
    public String getAllUsers(Model model) {
        log.info("Sending all users");
        List<UserInformation> users = userService.findAll();
        model.addAttribute("userList", users);
        return "user/readAll";
    }

    @GetMapping(path = "/{id}/edit")
    public String getUserById(@PathVariable int id, Model model) {
        log.info("Sending user {} for update", id);
        model.addAttribute("userInformation", userService.findById(id));
        model.addAttribute("roles", userService.findAllRoles());
        return "user/update";
    }

    @PatchMapping(path = "/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute UserInformation userInformation) {
        log.info("Updating user {}", id);
        userInformation.setId(id);
        userService.update(userInformation);
        return "redirect:";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable int id) {
        log.info("Deleting user {}", id);
        userService.deleteById(id);
        return "redirect:";
    }
}
