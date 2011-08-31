package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.City;
import models.calais.jpa.entities.Company;
import models.calais.jpa.entities.ProvinceOrState;


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
