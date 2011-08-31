package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Country")
public class Country extends CalaisGeoEntity {
    private static final long serialVersionUID = -3401948472486823461L;
}
