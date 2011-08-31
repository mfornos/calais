package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Company;

@Entity
@DiscriminatorValue("Buybacks")
public class Buybacks extends Fact {
    private static final long serialVersionUID = -4118173341961824229L;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;
    public String status;
    public String date;
    public String dateString;
}
