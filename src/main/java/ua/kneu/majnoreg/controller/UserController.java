package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kneu.majnoreg.entity.Declaration;
import ua.kneu.majnoreg.entity.User;
import ua.kneu.majnoreg.entity.UserCredentials;
import ua.kneu.majnoreg.entity.UserInformation;
import ua.kneu.majnoreg.service.DeclarationService;
import ua.kneu.majnoreg.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {
    private final DeclarationService declarationService;

    @GetMapping("/declarations")
    public String getUserDeclarationList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInformation user = ((UserCredentials) auth.getPrincipal()).getUserInformation();
        System.out.println((auth.getAuthorities()));
        log.info(user.toString());
        List<Declaration> declarations = declarationService.findDeclarationsByUserId(user.getId());
        //model.addAttribute("userLogin", user.getLogin());
        model.addAttribute("declarations", declarations);
        log.info("Found {} declarations for user [}", declarations.size(), user.getId());
        return "declaration/readMy";
    }

    @GetMapping(path = "/{id}/edit")
    public String getDeclarationById(@PathVariable int id, Model model) {
        model.addAttribute("declaration", declarationService.findById(id));
        return "declaration/update";
    }

}
