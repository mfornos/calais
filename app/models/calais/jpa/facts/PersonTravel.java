package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Person;

@Entity
@DiscriminatorValue("PersonTravel")
public class PersonTravel extends Fact {
    private static final long serialVersionUID = 6192061446216204280L;
    @OneToOne(cascade = CascadeType.ALL)
    public Person person;
    public String locationOrigin;
    public String locationDestination;
    public String status;
    public String date;
    public String dateString;
}
