package com.project.webapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/main_tables")
    public String getTables() {
        return "redirect:/airplane";
    }
}
