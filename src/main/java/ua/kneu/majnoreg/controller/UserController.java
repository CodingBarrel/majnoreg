package ua.kneu.majnoreg.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.kneu.majnoreg.entity.User;
import ua.kneu.majnoreg.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(path = {"/users", "/users/"})
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = {"/register","/register/"})
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());
        log.info("Sending user register form");
        return "user/register";
    }

    @PostMapping(path = {"/register","/register/"})
    public String addNewUser(@ModelAttribute User user) {
        user.setRoleId(1);
        log.info("Received user to register:" + user);
        if (userRepository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with specified id already exists");
        }
        userRepository.save(user);
        return "redirect:";
    }

    @GetMapping(path = {"","/"})
    public String getAllUsers(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "user/readAll";
    }

    @GetMapping(path = {"/{id}/edit","/{id}/edit/"})
    public String getUserById(@PathVariable int id, Model model) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            model.addAttribute("user", user.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        return "user/update";
    }

    @PatchMapping(path = {"/{id}","/{id}/"})
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        User updatedUser;
        if (optionalUser.isPresent()) {
            updatedUser = optionalUser.get();
            updatedUser.setId(id);
            updatedUser.setRoleId(id);
            userRepository.save(user);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        return "redirect:";
    }

    @DeleteMapping(path = {"/{id}", "/{id}/"})
    public String deleteUser(@PathVariable int id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        return "redirect:";
    }
}
