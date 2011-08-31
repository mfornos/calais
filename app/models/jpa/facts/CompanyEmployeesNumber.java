package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Company;

@Entity
@DiscriminatorValue("CompanyEmployeesNumber")
public class CompanyEmployeesNumber extends Fact {

    private static final long serialVersionUID = -5880919075511767663L;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;

    public String employeesNumber;

    public String location;

    public String unit;

    public String locationType;
}
