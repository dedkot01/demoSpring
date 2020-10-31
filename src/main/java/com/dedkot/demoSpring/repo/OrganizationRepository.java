package com.dedkot.demoSpring.repo;

import com.dedkot.demoSpring.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий сущности {@code Organization}
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    /**
     * Поиск организации, в которой устроен указанный сотрудник.
     * @param id Идентификатор сотрудника
     * @return Организация, в которой устроен сотрудник
     */
    @Query("select org from Organization org join Contract con on org.id = con.idOrganization where con.idEmployee = :id")
    Organization findOrganizationByIdEmployee(Long id);

}
