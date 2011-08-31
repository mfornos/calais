package models.calais.jpa.facts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import models.calais.jpa.entities.Person;

@Entity
@DiscriminatorValue("PersonRelation")
public class PersonRelation extends Fact {
    private static final long serialVersionUID = 9079698326252629134L;
    @ManyToMany(cascade = CascadeType.ALL)
    public List<Person> person = new ArrayList<Person>();
    public String personRelationType;
    public String date;
    public String dateString;
}
