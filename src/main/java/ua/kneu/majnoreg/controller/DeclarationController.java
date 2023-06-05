package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kneu.majnoreg.entity.Declaration;
import ua.kneu.majnoreg.entity.UserCredentials;
import ua.kneu.majnoreg.entity.UserInformation;
import ua.kneu.majnoreg.service.DeclarationService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/declarations")
public class DeclarationController {
    private final DeclarationService declarationService;

    @GetMapping(path = "/create")
    public String getRegisterForm(Model model) {
        model.addAttribute("declaration", new Declaration());
        model.addAttribute("propertyTypes", declarationService.findAllPropertyTypes());
        log.info("Sending declaration create form");
        return "declaration/create";
    }

    @PostMapping(path = "/create")
    public String createNewDeclaration(@ModelAttribute Declaration declaration) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials user = (UserCredentials) auth.getPrincipal();
        declaration.setUserInformation(user.getUserInformation());
        declarationService.create(declaration);
        return "redirect:";
    }

    @GetMapping(path = "")
    public String getAllDeclarations(Model model) {
        List<Declaration> declarations = declarationService.findAll();
        model.addAttribute("declarations", declarations);
        return "declaration/readAll";
    }

    @GetMapping(path = "/{id}/edit")
    public String getDeclarationById(@PathVariable int id, Model model) {
        model.addAttribute("declaration", declarationService.findById(id));
        model.addAttribute("propertyTypes", declarationService.findAllPropertyTypes());
        return "declaration/update";
    }

    @PatchMapping(path = "/{id}")
    public String updateDeclaration(@PathVariable int id, @ModelAttribute Declaration declaration) {
        declaration.setId(id);
        declarationService.update(declaration);
        return "redirect:";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteDeclaration(@PathVariable int id) {
        declarationService.deleteById(id);
        return "redirect:";
    }
}
