package ua.kneu.majnoreg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.kneu.majnoreg.entity.User;
import ua.kneu.majnoreg.repository.UserRepository;

@Controller
@RequestMapping(path = "/user")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String surname,
                                           @RequestParam String middlename){
        User user = new User();
        user.setRoleId(1);
        user.setName(name);
        user.setSurname(surname);
        user.setMiddlename(middlename);
        userRepository.save(user);
        return "success";
    }

    @GetMapping(path = "/addexamle")
    public @ResponseBody String addNewUser(){
        User user = new User();
        user.setRoleId(1);
        user.setName("name");
        user.setSurname("surname");
        user.setMiddlename("middlename");
        userRepository.save(user);
        return "success";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
