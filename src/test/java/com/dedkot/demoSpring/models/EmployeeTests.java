package com.dedkot.demoSpring.models;

import com.dedkot.demoSpring.repo.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeTests {

    @Autowired
    EmployeeRepository empRepo;

    @Test
    void employeeEquals() {
        Employee emp1 = new Employee("Test1");
        Employee emp2 = new Employee("Test2");
        Employee emp3 = new Employee();
        Employee emp4 = new Employee("Test1");

        assertFalse(emp1.equals(emp2));
        assertFalse(emp1.equals(emp3));
        assertTrue(emp1.equals(emp4));
        assertEquals(emp1, emp4);
    }

    @Test
    void employeeCRUD() {
        /*Employee employee = new Employee("Test1");
        employee.setId(0L);
        empRepo.save(employee);
        System.out.println(employee);

        Employee empDB = empRepo.getOne(0L);
        System.out.println(empDB);

        assertEquals(employee, empDB);

        employee.setName("Test2");
        empRepo.save(employee);
        assertEquals(employee, empRepo.getOne(0L));

        empRepo.delete(employee);*/
    }
}
