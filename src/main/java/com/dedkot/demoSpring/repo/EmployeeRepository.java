package com.dedkot.demoSpring.repo;

import com.dedkot.demoSpring.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select emp from Employee emp join Contract con on emp.id = con.idEmployee where con.idOrganization = :id")
    List<Employee> findAllEmployeesByIdOrganization(Long id);

    @Query("select emp from Employee emp left outer join Contract con on emp.id = con.idEmployee where con.idEmployee is null")
    List<Employee> findAllFreeEmployees();
}
