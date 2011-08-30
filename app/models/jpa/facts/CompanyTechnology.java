package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Company;
import models.jpa.entities.Technology;

@Entity
@DiscriminatorValue("CompanyTechnology")
public class CompanyTechnology extends Fact {
    private static final long serialVersionUID = 4917638705879182889L;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;
    
    @OneToOne(cascade = CascadeType.ALL)
    public Technology technology;
}
