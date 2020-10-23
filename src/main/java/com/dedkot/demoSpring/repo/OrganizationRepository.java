package com.dedkot.demoSpring.repo;

import com.dedkot.demoSpring.models.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
}
