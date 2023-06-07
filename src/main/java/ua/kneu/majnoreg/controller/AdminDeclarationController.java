package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kneu.majnoreg.entity.Declaration;
import ua.kneu.majnoreg.entity.dict.DeclarationStatus;
import ua.kneu.majnoreg.service.DeclarationService;

import java.time.LocalDateTime;
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
        model.addAttribute("declarationStatuses", declarationService.findAllDeclarationsStatuses());
        return "admin-declaration/verify";
    }

    @PatchMapping(path = "/{id}")
    public String updateDeclaration(@PathVariable int id, @RequestParam int statusId) {
        log.info("Request to update declaration {} status id to {}", id, statusId);
        Declaration declaration = declarationService.findById(id);
        declaration.setStatus(declarationService.findDeclarationStatusById(statusId));
        declaration.setLastUpdateTime(LocalDateTime.now());
        declarationService.update(declaration);
        return "redirect:/admin-declaration";
    }

    @GetMapping(path = "")
    public String getAllDeclarations(Model model) {
        List<Declaration> declarations = declarationService.findAll();
        model.addAttribute("declarations", declarations);
        return "admin-declaration/readAll";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteDeclaration(@PathVariable int id) {
        declarationService.deleteById(id);
        return "redirect:/admin-declaration";
    }
}
