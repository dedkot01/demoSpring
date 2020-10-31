package com.dedkot.demoSpring.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contract {
    @Id
    private Long idEmployee;

    private Long idOrganization;

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Long getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Long idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Contract(Long idEmployee, Long idOrganization) {
        this.idEmployee = idEmployee;
        this.idOrganization = idOrganization;
    }

    public Contract() {
    }
}
