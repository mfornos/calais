package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Company;


@Entity
@DiscriminatorValue("Bankruptcy")
public class Bankruptcy extends Fact {

    private static final long serialVersionUID = 1019559174541279916L;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;
    public String status;
    public String date;
    public String dateString;
}
