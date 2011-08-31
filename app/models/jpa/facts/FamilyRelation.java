package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Person;

@Entity
@DiscriminatorValue("FamilyRelation")
public class FamilyRelation extends Fact {
    private static final long serialVersionUID = 388826411200112549L;

    @OneToOne(cascade = CascadeType.ALL)
    public Person person;

    @OneToOne(cascade = CascadeType.ALL)
    public Person person_relative;

    public String familyRelationType;
}
