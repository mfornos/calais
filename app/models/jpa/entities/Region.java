package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Region")
public class Region extends CalaisEntity {

    private static final long serialVersionUID = -1811492801978660821L;

}
