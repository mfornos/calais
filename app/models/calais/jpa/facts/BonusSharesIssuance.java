package models.calais.jpa.facts;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Company;


@Entity
@DiscriminatorValue("BonusSharesIssuance")
public class BonusSharesIssuance extends Fact {

    private static final long serialVersionUID = -6340477492000939955L;

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;
    public String bonusSharesRatio;
    public String status;
    public String date;
    public String dateString;
}
