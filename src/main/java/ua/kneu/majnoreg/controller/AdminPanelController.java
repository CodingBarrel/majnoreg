package ua.kneu.majnoreg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kneu.majnoreg.entity.dict.DeclarationStatus;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin-panel")
public class AdminPanelController {

    @GetMapping()
    public String getAdminPanel() {
        return "admin-panel";
    }

}
