package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Company;


@Entity
@DiscriminatorValue("Acquisition")
public class Acquisition extends Fact {
    private static final long serialVersionUID = -5146954737437433702L;
    @OneToOne(cascade = CascadeType.ALL)
    public Company company_Acquirer;
    @OneToOne(cascade = CascadeType.ALL)
    public Company company_BeingAcquired;
    public String status;
    public String date;
    public String dateString;
}
