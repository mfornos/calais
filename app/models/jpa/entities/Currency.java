package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Currency")
public class Currency extends CalaisEntity {

    private static final long serialVersionUID = 4182910979075133L;

}
