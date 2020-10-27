package com.dedkot.demoSpring.repo;

import com.dedkot.demoSpring.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepostitory extends JpaRepository<Contract, Long> {
    List<Contract> findAllByIdOrganization(Long idOrganization);
}
