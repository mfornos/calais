package models.calais.jpa.facts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import models.calais.jpa.entities.Company;


@Entity
@DiscriminatorValue("BusinessRelation")
public class BusinessRelation extends Fact {

    private static final long serialVersionUID = 7726912713723066400L;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Company> company = new ArrayList<Company>();
    public String businessRelationType;
    public String status;
    public String date;
    public String dateString;
}
