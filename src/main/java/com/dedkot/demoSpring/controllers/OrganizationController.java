package com.dedkot.demoSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @GetMapping
    public String organization(Model model) {
        return "organization/organization";
    }
    
}
