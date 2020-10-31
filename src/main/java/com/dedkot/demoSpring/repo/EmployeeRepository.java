package com.dedkot.demoSpring.repo;

import com.dedkot.demoSpring.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий сущности Employee
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Поиск сотрудников, устроенных в указанной организации.
     * @param id Идентификатор организации
     * @return Список сотрудников
     */
    @Query("select emp from Employee emp join Contract con on emp.id = con.idEmployee where con.idOrganization = :id")
    List<Employee> findAllEmployeesByIdOrganization(Long id);

    /**
     * Поиск сотрудников, не принадлежащих какой-либо организации.
     * @return Список свободных сотрудников
     */
    @Query("select emp from Employee emp left outer join Contract con on emp.id = con.idEmployee where con.idEmployee is null")
    List<Employee> findAllFreeEmployees();

}
