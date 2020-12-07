package com.dedkot.demoSpring.models;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinTable(name = "EMPLOYEE_ORGANIZATION",
                joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
                inverseJoinColumns = @JoinColumn(nullable = false)
    )
    private Organization organization;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Employee) {
            if (name != null ? name.equals(((Employee) obj).name) : name == ((Employee) obj).name)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return id + " | " + name;
    }

}
