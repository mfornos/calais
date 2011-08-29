package models.jpa.facts;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import models.jpa.CalaisDocument;
import models.jpa.DetectionBase;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "fact_type", discriminatorType = DiscriminatorType.STRING)
public class Fact extends DetectionBase {

    private static final long serialVersionUID = -6252058212487295868L;

    @ManyToMany(mappedBy = "facts")
    public Set<CalaisDocument> documents = new HashSet<CalaisDocument>();
}
