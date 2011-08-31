package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PhoneNumber")
public class PhoneNumber extends CalaisEntity {

    private static final long serialVersionUID = -1335436197503559662L;

}
