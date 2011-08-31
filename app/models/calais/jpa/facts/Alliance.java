package models.calais.jpa.facts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import models.calais.jpa.entities.Company;


@Entity
@DiscriminatorValue("Alliance")
public class Alliance extends Fact {

    private static final long serialVersionUID = 7190594132855776544L;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Company> company = new ArrayList<Company>();

    public String dateString;

    public String data;
}
