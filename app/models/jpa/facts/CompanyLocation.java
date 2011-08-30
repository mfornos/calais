package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.City;
import models.jpa.entities.Company;
import models.jpa.entities.ProvinceOrState;

@Entity
@DiscriminatorValue("CompanyLocation")
public class CompanyLocation extends Fact {

    private static final long serialVersionUID = 82263761447920428L;

    public String companyLocationType;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;

    @OneToOne(cascade = CascadeType.ALL)
    public City city;

    @OneToOne(cascade = CascadeType.ALL)
    public ProvinceOrState provinceOrState;
}
