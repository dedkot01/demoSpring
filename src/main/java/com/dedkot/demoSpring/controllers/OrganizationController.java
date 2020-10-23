package com.dedkot.demoSpring.controllers;

import com.dedkot.demoSpring.models.Organization;
import com.dedkot.demoSpring.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationRepository orgRepo;

    @GetMapping
    public String organization(Model model) {
        Iterable<Organization> organizations = orgRepo.findAll();
        model.addAttribute("organizations", organizations);
        return "organization/organization";
    }

    @GetMapping("/new")
    public String organizationNew() {
        return "organization/new";
    }

    @PostMapping("/new")
    public String organizationPostNew(@RequestParam String name) {
        Organization organization = new Organization(name);
        orgRepo.save(organization);
        return "redirect:/organization";
    }
}
