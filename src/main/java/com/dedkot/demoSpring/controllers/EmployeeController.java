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

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private ContractRepostitory conRepo;

    @Autowired
    private OrganizationRepository orgRepo;


    @GetMapping
    public String employee(Model model) {
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);

        return "employee/employee";
    }

    @GetMapping("/{id}")
    public String employeeDetails(@PathVariable Long id,
                                  Model model) {
        Employee employee = empRepo.findById(id).get();
        model.addAttribute("employee", employee);

        Contract con = conRepo.findOneByIdEmployee(id);
        Organization org = null;
        if (con != null)
            org = orgRepo.findById(con.getIdOrganization()).get();
        model.addAttribute("organization", org);

        return "employee/details";
    }

    @GetMapping("/{id}/edit")
    public String employeeEdit(@PathVariable Long id,
                               Model model) {
        Employee employee = empRepo.findById(id).get();
        model.addAttribute("employee", employee);

        return "employee/edit";
    }

    @PostMapping("/{id}/edit")
    public String employeePostEdit(@PathVariable Long id,
                                   @RequestParam String name) {
        Employee employee = empRepo.findById(id).get();
        employee.setName(name);
        empRepo.save(employee);

        return "redirect:/employee/" + id;
    }

    @PostMapping("/{id}/remove")
    public String employeeRemove(@PathVariable Long id) {
        Employee employee = empRepo.findById(id).get();
        empRepo.delete(employee);

        return "redirect:/employee";
    }

    @GetMapping("/new")
    public String employeeNew() {
        return "employee/new";
    }

    @PostMapping("/new")
    public String employeePostNew(@RequestParam String name) {
        Employee employee = new Employee(name);
        empRepo.save(employee);

        return "redirect:/employee";
    }

}
