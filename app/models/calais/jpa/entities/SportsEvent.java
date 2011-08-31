package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SportsEvent")
public class SportsEvent extends CalaisEntity {

    private static final long serialVersionUID = 5897010132957390710L;

}
