package models.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.jpa.entities.Company;

@Entity
@DiscriminatorValue("ConferenceCall")
public class ConferenceCall extends Fact {

    private static final long serialVersionUID = -784325817813305367L;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;
    public String status;
    public String date;
    public String dateString;
    public String quarter;
    public String conferenceCallType;
}
