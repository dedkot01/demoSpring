package com.dedkot.demoSpring.controllers;

import com.dedkot.demoSpring.models.Employee;
import com.dedkot.demoSpring.models.Organization;
import com.dedkot.demoSpring.repo.EmployeeRepository;
import com.dedkot.demoSpring.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationRepository orgRepo;

    @Autowired
    private EmployeeRepository empRepo;

    /**
     * Вывод списка всех организаций.
     */
    @GetMapping
    public String organization(Model model) {
        List<Organization> organizations = orgRepo.findAll();

        model.addAttribute("organizations", organizations);

        return "organization/organization";
    }

    /**
     * Форма создания новой организации.
     */
    @GetMapping("/new")
    public String organizationNew() {
        return "organization/new";
    }

    /**
     * Сохранения в БД новой организации.
     * @param name Название организации
     * @param description Описание организации
     */
    @PostMapping("/new")
    public String organizationPostNew(@RequestParam String name, @RequestParam String description) {
        Organization organization = new Organization(name, description);

        orgRepo.save(organization);

        return "redirect:/organization";
    }

    /**
     * Вывод данных об организации.
     * @param id Идентификатор организации
     */
    @GetMapping("/{id}")
    public String organizationDetails(@PathVariable("id") Long id,
                                      Model model) {
        Organization organization = orgRepo.findById(id).get();

        model.addAttribute("organization", organization);
        model.addAttribute("employees", organization.getEmployees());

        return "organization/details";
    }

    /**
     * Форма редактирования данных об организации.
     * @param id Идентификатор организации
     */
    @GetMapping("/{id}/edit")
    public String organizationEdit(@PathVariable("id") Long id,
                                      Model model) {
        Organization organization = orgRepo.findById(id).get();

        model.addAttribute("organization", organization);

        return "organization/edit";
    }

    /**
     * Обновление данных в БД об организации.
     * @param id Идентификатор организации
     * @param name Новое название организации
     * @param description Новое описание организации
     */
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

    /**
     * Удаление из БД указанную организацию.
     * @param id Идентификатор удаляемой организации
     */
    @PostMapping("/{id}/remove")
    public String organizationPostRemove(@PathVariable("id") Long id) {
        Organization organization = orgRepo.findById(id).get();

        orgRepo.delete(organization);

        return "redirect:/organization";
    }

    /**
     * Страница наёма нового сотрудника (из списка свободных) в организацию.
     * @param id Идентификатор организации
     */
    @GetMapping("/{id}/addEmployee")
    public String organizationAddEmployee(@PathVariable("id") Long id, Model model) {
        Organization organization = orgRepo.findById(id).get();
        List<Employee> freeEmployees = empRepo.findAllFree();

        model.addAttribute("organization", organization);
        model.addAttribute("freeEmployees", freeEmployees);

        return "organization/addEmployee";
    }

    /**
     * Сохранение записи в БД о новом сотруднике организации.
     * @param id Идентификатор организации
     * @param idEmployee Идентификатор нового сотрудника организации
     */
    @PostMapping("/{id}/addEmployee")
    public String organizationPostAddEmployee(@PathVariable("id") Long id,
                                              @RequestParam Long idEmployee) {
        Employee employee = empRepo.findById(idEmployee).get();
        Organization organization = orgRepo.findById(id).get();

        employee.setOrganization(organization);
        empRepo.save(employee);

        return "redirect:/organization/" + id;
    }

    /**
     * Увольнение сотрудника из организации.
     * @param id Идентификатор организации
     * @param idEmployee Идентификатор увольняемого сотрудника
     */
    @PostMapping("/{id}/dismissEmployee")
    public String organizationPostDismissEmployee(@PathVariable("id") Long id,
                                                  @RequestParam Long idEmployee) {
        Employee employee = empRepo.findById(idEmployee).get();

        employee.setOrganization(null);
        empRepo.save(employee);

        return "redirect:/organization/" + id;
    }

}
