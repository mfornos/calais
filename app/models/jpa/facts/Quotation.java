package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import models.jpa.entities.Person;

@Entity
@DiscriminatorValue("Quotation")
public class Quotation extends Fact {

    private static final long serialVersionUID = 7291777896455834817L;

    @Lob
    public String quote;

    @OneToOne(cascade = CascadeType.ALL)
    public Person person;

    public String personDescription;
}
