package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Person;

@Entity
@DiscriminatorValue("PersonCareer")
public class PersonEducation extends Fact {
    private static final long serialVersionUID = -3049647770997654190L;
    @OneToOne(cascade = CascadeType.ALL)
    public Person person;
    public String certification;
    public String degree;
    public String schoolOrOrganization;
}
