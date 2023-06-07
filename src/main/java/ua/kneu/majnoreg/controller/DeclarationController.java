package ua.kneu.majnoreg.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kneu.majnoreg.entity.Declaration;
import ua.kneu.majnoreg.entity.UserCredentials;
import ua.kneu.majnoreg.entity.UserInformation;
import ua.kneu.majnoreg.entity.dict.DeclarationStatus;
import ua.kneu.majnoreg.entity.dict.PropertyType;
import ua.kneu.majnoreg.service.DeclarationService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/declarations")
public class DeclarationController {
    public static final int DECLARATION_STATUS_ID_BY_DEFAULT = 1;
    private final DeclarationService declarationService;

    @GetMapping(path = "/create")
    public String getRegisterForm(Model model) {
        log.info("Request to send declaration create form");
        model.addAttribute("declaration", new Declaration());
        model.addAttribute("propertyTypes", declarationService.findAllPropertyTypes());
        log.info("Sending declaration create form");
        return "declaration/create";
    }

    @PostMapping(path = "/create")
    public String createNewDeclaration(@ModelAttribute Declaration declaration) {
        log.info("Request to create declaration {}", declaration);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials user = (UserCredentials) auth.getPrincipal();
        declaration.setUserInformation(user.getUserInformation());
        declaration.setStatus(declarationService.findDeclarationStatusById(DECLARATION_STATUS_ID_BY_DEFAULT));
        declaration.setSendTime(LocalDateTime.now());
        declaration.setLastUpdateTime(LocalDateTime.now());
        declarationService.create(declaration);
        log.info("Created declaration {}", declaration);
        return "redirect:/user/declarations";
    }

    @GetMapping("/search")
    public String searchDeclarations(HttpServletRequest request,
                                     @RequestParam(required = false) Integer propertyTypeId,
                                     @RequestParam(required = false) String address,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     Model model) {
        log.info("Request to search for declarations PTI {}, address {}, page {}, size {}", propertyTypeId, address, page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Declaration> declarations;
        if (propertyTypeId != null && address != null) {
            declarations = declarationService.findByPropertyTypeIdAndAddressContaining(propertyTypeId, address,pageable);
        } else {
            declarations = declarationService.findAll(pageable);
        }
        model.addAttribute("declarations", declarations);
        List<PropertyType> propertyTypes = declarationService.findAllPropertyTypes();
        model.addAttribute("propertyTypes", propertyTypes);
        model.addAttribute("request", request);
        log.info("Sending {} found declarations", declarations.getTotalElements());
        return "declaration/readAll";
    }

    @GetMapping(path = "/{id}/edit")
    public String getDeclarationById(@PathVariable int id, Model model) {
        log.info("Request to edit declaration {}", id);
        model.addAttribute("declaration", declarationService.findById(id));
        model.addAttribute("propertyTypes", declarationService.findAllPropertyTypes());
        log.info("Sending found declaration to edit");
        return "declaration/update";
    }

    @PatchMapping(path = "/{id}")
    public String updateDeclaration(@PathVariable int id, @ModelAttribute Declaration declaration) {
        declaration.setId(id);
        declaration.setLastUpdateTime(LocalDateTime.now());
        declarationService.update(declaration);
        return "redirect:";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteDeclaration(@PathVariable int id) {
        declarationService.deleteById(id);
        return "redirect:";
    }
}
