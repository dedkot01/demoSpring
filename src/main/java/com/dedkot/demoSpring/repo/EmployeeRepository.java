package com.dedkot.demoSpring.repo;

import com.dedkot.demoSpring.models.Employee;
import com.dedkot.demoSpring.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Репозиторий сущности Employee
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select emp from Employee emp where emp.organization = :organization")
    List<Employee> findAllByOrganization(Organization organization);

    @Query("select emp from Employee emp where emp.organization = null")
    List<Employee> findAllFree();

    @Transactional
    @Modifying
    @Query("update Employee emp set emp.organization = null where emp.organization = :organization")
    void dismissAllEmployeesByOrganization(Organization organization);

}
