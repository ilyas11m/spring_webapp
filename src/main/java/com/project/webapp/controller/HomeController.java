package com.project.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/home/{color}")
    public String home(@PathVariable String color, String username,
                       Model page)
    {
        page.addAttribute("username", "Ilyas");
        page.addAttribute("color", color);
        return "home.html";
    }
}
