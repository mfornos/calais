package models.calais.jpa.facts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import models.calais.jpa.entities.Facility;
import models.calais.jpa.entities.Person;

@Entity
@DiscriminatorValue("PersonCommunication")
public class PersonCommunication extends Fact {

    private static final long serialVersionUID = 7233709814010518607L;
    @ManyToMany(cascade = CascadeType.ALL)
    public List<Person> person = new ArrayList<Person>();
    @OneToOne(cascade = CascadeType.ALL)
    public Facility facility;
    public String status;
    public String date;
    public String dateString;
}
