package models.jpa.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.City;
import models.jpa.entities.Company;
import models.jpa.entities.Country;
import models.jpa.entities.Organization;
import models.jpa.entities.Person;
import models.jpa.entities.Position;
import models.jpa.entities.ProvinceOrState;

@Entity
@DiscriminatorValue("PersonCareer")
public class PersonCareer extends Fact {

    private static final long serialVersionUID = -8256910653823430857L;

    @OneToOne
    public Person person;

    public String careerType;

    @OneToOne
    public Position position;

    @OneToOne
    public Company company;

    @OneToOne
    public Organization organization;

    @OneToOne
    public Country country;

    @OneToOne
    public ProvinceOrState provinceOrState;

    @OneToOne
    public City city;

    public String status;
}
