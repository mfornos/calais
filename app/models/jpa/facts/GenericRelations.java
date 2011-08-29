package models.jpa.facts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GenericRelations")
public class GenericRelations extends Fact {

    private static final long serialVersionUID = -4835610780745187803L;

    public String relationObject;

    public String relationSubject;

    public String verb;
}
