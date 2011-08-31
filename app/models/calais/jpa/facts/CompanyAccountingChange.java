package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Company;


@Entity
@DiscriminatorValue("CompanyAccountingChange")
public class CompanyAccountingChange extends Fact {

    private static final long serialVersionUID = -6756188040011199614L;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;
    public String accountingChangeType;
    public String status;
    public String date;
    public String dateString;
}
