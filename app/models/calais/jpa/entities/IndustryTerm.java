package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IndustryTerm")
public class IndustryTerm extends CalaisEntity {

    private static final long serialVersionUID = 3614385101200437062L;

}
