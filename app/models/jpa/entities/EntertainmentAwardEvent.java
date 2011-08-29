package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EntertainmentAwardEvent")
public class EntertainmentAwardEvent extends CalaisEntity {

    private static final long serialVersionUID = -6873704673186894567L;

}
