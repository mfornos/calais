package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Person;


@Entity
@DiscriminatorValue("PersonAttributes")
public class PersonAttributes extends Fact {

    private static final long serialVersionUID = 7867113622637035248L;

    @OneToOne(cascade = CascadeType.ALL)
    public Person person;

    public int age;

    public String gender;

    public String birthPlace;

    public String birthDate;
}
