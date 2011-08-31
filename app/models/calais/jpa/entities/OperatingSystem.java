package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OperatingSystem")
public class OperatingSystem extends CalaisEntity {

    private static final long serialVersionUID = -8594330713972428076L;

}
