package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.EmailAddress;
import models.jpa.entities.Person;

@Entity
@DiscriminatorValue("PersonEmailAddress")
public class PersonEmailAddress extends Fact {

    private static final long serialVersionUID = 4948478217223419301L;
    @OneToOne(cascade = CascadeType.ALL)
    public Person person;
    @OneToOne(cascade = CascadeType.ALL)
    public EmailAddress emailAddress;
}
