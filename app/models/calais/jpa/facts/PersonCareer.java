package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.City;
import models.calais.jpa.entities.Company;
import models.calais.jpa.entities.Country;
import models.calais.jpa.entities.Organization;
import models.calais.jpa.entities.Person;
import models.calais.jpa.entities.Position;
import models.calais.jpa.entities.ProvinceOrState;

@Entity
@DiscriminatorValue("PersonCareer")
public class PersonCareer extends Fact {

    private static final long serialVersionUID = -8256910653823430857L;

    @OneToOne(cascade = CascadeType.ALL)
    public Person person;

    public String careerType;

    @OneToOne(cascade = CascadeType.ALL)
    public Position position;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;

    @OneToOne(cascade = CascadeType.ALL)
    public Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    public Country country;

    @OneToOne(cascade = CascadeType.ALL)
    public ProvinceOrState provinceOrState;

    @OneToOne(cascade = CascadeType.ALL)
    public City city;

    public String status;
}
