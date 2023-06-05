package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kneu.majnoreg.entity.Declaration;
import ua.kneu.majnoreg.service.DeclarationService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin-declarations")
public class AdminDeclarationController {
    private final DeclarationService declarationService;

    @GetMapping(path = "/{id}/verify")
    public String getDeclarationById(@PathVariable int id, Model model) {
        model.addAttribute("declaration", declarationService.findById(id));
        return "declaration/update";
    }

    @GetMapping(path = "")
    public String getAllDeclarations(Model model) {
        List<Declaration> declarations = declarationService.findAll();
        model.addAttribute("declarations", declarations);
        return "declaration/readAll";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteDeclaration(@PathVariable int id) {
        declarationService.deleteById(id);
        return "redirect:";
    }
}
