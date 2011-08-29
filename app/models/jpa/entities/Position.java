package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Position")
public class Position extends CalaisEntity {

    private static final long serialVersionUID = -3832281701239203174L;

}
