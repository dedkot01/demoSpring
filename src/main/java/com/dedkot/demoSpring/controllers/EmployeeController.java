package com.dedkot.demoSpring.controllers;

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

    /**
     * Вывод списка всех сотрудников.
     */
    @GetMapping
    public String employee(Model model) {
        List<Employee> employees = empRepo.findAll();

        model.addAttribute("employees", employees);

        return "employee/employee";
    }

    /**
     * Форма создания нового сотрудника.
     */
    @GetMapping("/new")
    public String employeeNew() {
        return "employee/new";
    }

    /**
     * Сохранения в БД нового сотрудника.
     * @param name Имя сотрудника
     */
    @PostMapping("/new")
    public String employeePostNew(@RequestParam String name) {
        Employee employee = new Employee(name);

        empRepo.save(employee);

        return "redirect:/employee";
    }

    /**
     * Вывод данных о сотруднике.
     * @param id Идентификатор сотрудника
     */
    @GetMapping("/{id}")
    public String employeeDetails(@PathVariable Long id,
                                  Model model) {
        Employee employee = empRepo.findById(id).get();
        Organization organization = orgRepo.findOrganizationByIdEmployee(id);

        model.addAttribute("employee", employee);
        model.addAttribute("organization", organization);

        return "employee/details";
    }

    /**
     * Форма редактирования данных о сотруднике.
     * @param id Идентификатор сотрудника
     */
    @GetMapping("/{id}/edit")
    public String employeeEdit(@PathVariable Long id,
                               Model model) {
        Employee employee = empRepo.findById(id).get();

        model.addAttribute("employee", employee);

        return "employee/edit";
    }

    /**
     * Обновление данных в БД о сотруднике.
     * @param id Идентификатор сотрудника
     * @param name Новое имя сотрудника
     */
    @PostMapping("/{id}/edit")
    public String employeePostEdit(@PathVariable Long id,
                                   @RequestParam String name) {
        Employee employee = empRepo.findById(id).get();
        employee.setName(name);

        empRepo.save(employee);

        return "redirect:/employee/" + id;
    }

    /**
     * Удаление из БД указанного сотрудника.
     * @param id Идентификатор удаляемого сотрудника
     */
    @PostMapping("/{id}/remove")
    public String employeeRemove(@PathVariable Long id) {
        Employee employee = empRepo.findById(id).get();

        empRepo.delete(employee);

        return "redirect:/employee";
    }

}
