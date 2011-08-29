package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Holiday")
public class Holiday extends CalaisEntity {

    private static final long serialVersionUID = 5982919787565683718L;

}
