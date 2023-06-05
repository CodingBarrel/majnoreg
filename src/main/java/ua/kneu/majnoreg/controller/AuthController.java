package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kneu.majnoreg.entity.User;
import ua.kneu.majnoreg.entity.UserCredentials;
import ua.kneu.majnoreg.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping(path = "/register")
    public String getRegisterForm(Model model) {
        log.info("Sending user register form");
        model.addAttribute("user", new User());
        return "auth/register";
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
        return "auth/login";
    }
}
