package com.dedkot.demoSpring.controllers;

import com.dedkot.demoSpring.models.Contract;
import com.dedkot.demoSpring.models.Employee;
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

        /*
        В будущем заменить на запрос:
        select emp.* from employee emp join contract con on emp.id = con.id_employee where con.id_organization = {id}
         */
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
                                       @RequestParam String name, @RequestParam String description,
                                       Model model) {
        Organization organization = orgRepo.findById(id).get();
        organization.setName(name);
        organization.setDescription(description);
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
    public String organizationPostNew(@RequestParam String name, @RequestParam String description) {
        Organization organization = new Organization(name, description);
        orgRepo.save(organization);

        return "redirect:/organization";
    }



    @GetMapping("/{id}/addEmployee")
    public String organizationAddEmployee(@PathVariable("id") Long id, Model model) {
        /*
        В будущем заменить на запрос:
        select emp.* from employee emp left outer join contract con on emp.id = con.id_employee where con.id_employee is null
         */
        List<Contract> contracts = conRepo.findAll();
        List<Employee> freeEmployees = empRepo.findAll();



        for (int i = 0; i < contracts.size(); i++) {
            for (int j = 0; j < freeEmployees.size(); j++) {
                if (freeEmployees.get(j).getId() == contracts.get(i).getIdEmployee()) {
                    freeEmployees.remove(j);
                    break;
                }
            }
        }

        model.addAttribute("organization", orgRepo.findById(id).get());
        model.addAttribute("freeEmployees", freeEmployees);

        return "organization/addEmployee";
    }

    @PostMapping("/{id}/addEmployee")
    public String organizationPostAddEmployee(@PathVariable("id") Long id,
                                              @RequestParam Long idEmployee) {
        Contract contract = new Contract(idEmployee, id);
        conRepo.save(contract);

        return "redirect:/organization/" + id;
    }

    @PostMapping("/{id}/dismissEmployee")
    public String organizationPostDismissEmployee(@PathVariable("id") Long id,
                                                  @RequestParam Long idEmployee) {
        Contract contract = conRepo.findOneByIdEmployee(idEmployee);
        conRepo.delete(contract);

        return "redirect:/organization/" + id;
    }
}
