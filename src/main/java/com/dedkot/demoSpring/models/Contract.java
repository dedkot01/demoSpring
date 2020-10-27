package com.dedkot.demoSpring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contract {
    @Id
    @GeneratedValue
    private Long id;

    private Long idEmployee;

    private Long idOrganization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
