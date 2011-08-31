package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SportsLeague")
public class SportsLeague extends CalaisEntity {

    private static final long serialVersionUID = 1219876482442408906L;

}
