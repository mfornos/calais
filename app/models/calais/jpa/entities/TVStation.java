package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TVStation")
public class TVStation extends CalaisEntity {

    private static final long serialVersionUID = 3858835700091108019L;

}
