package models.jpa.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Company;

@Entity
@DiscriminatorValue("CompanyTechnology")
public class CompanyTechnology extends Fact {
    private static final long serialVersionUID = 4917638705879182889L;

    @OneToOne
    public Company company;

    public String technology;
}
