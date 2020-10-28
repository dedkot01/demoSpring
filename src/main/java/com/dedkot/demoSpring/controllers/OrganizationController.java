package com.dedkot.demoSpring.controllers;

import com.dedkot.demoSpring.models.Organization;
import com.dedkot.demoSpring.repo.ContractRepostitory;
import com.dedkot.demoSpring.repo.EmployeeRepository;
import com.dedkot.demoSpring.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationRepository orgRepo;

    @Autowired
    private ContractRepostitory conRepo;

    @Autowired
    private EmployeeRepository empRepo;

    @GetMapping
    public String organization(Model model) {
        List<Organization> organizations = orgRepo.findAll();
        model.addAttribute("organizations", organizations);

        return "organization/organization";
    }

    @GetMapping("/{id}")
    public String organizationDetails(@PathVariable("id") Long id,
                                      Model model) {
        model.addAttribute("organization", orgRepo.findById(id).get());

        List<Long> idsEmployee = new ArrayList<>();
        conRepo.findAllByIdOrganization(id).forEach(el -> idsEmployee.add(el.getIdEmployee()));
        model.addAttribute("employees", empRepo.findAllById(idsEmployee));

        return "organization/details";
    }

    @GetMapping("/{id}/edit")
    public String organizationEdit(@PathVariable("id") Long id,
                                      Model model) {
        model.addAttribute("organization", orgRepo.findById(id).get());

        return "organization/edit";
    }

    @PostMapping("/{id}/edit")
    public String organizationPostEdit(@PathVariable("id") Long id,
                                       @RequestParam String name,
                                       Model model) {
        Organization organization = orgRepo.findById(id).get();
        organization.setName(name);
        orgRepo.save(organization);

        return "redirect:/organization/" + id;
    }

    @PostMapping("/{id}/remove")
    public String organizationPostRemove(@PathVariable("id") Long id) {
        Organization organization = orgRepo.findById(id).get();
        orgRepo.delete(organization);

        return "redirect:/organization";
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
