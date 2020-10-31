package com.dedkot.demoSpring.repo;

import com.dedkot.demoSpring.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий сущности Contract.
 */
@Repository
public interface ContractRepostitory extends JpaRepository<Contract, Long> {

    /**
     * Поиск записи по идентификатору сотрудника.
     * @param idEmployee Идентификатор сотрудника
     * @return Запись сущности Contract
     */
    Contract findOneByIdEmployee(Long idEmployee);

}
