package models.jpa.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Person;

@Entity
@DiscriminatorValue("Quotation")
public class Quotation extends Fact {

    private static final long serialVersionUID = 7291777896455834817L;

    public String quote;

    @OneToOne
    public Person person;

    public String personDescription;
}
