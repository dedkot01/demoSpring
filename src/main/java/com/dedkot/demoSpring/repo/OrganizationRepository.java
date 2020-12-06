package com.dedkot.demoSpring.repo;

import com.dedkot.demoSpring.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий сущности {@code Organization}
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
