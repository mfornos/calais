package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Continent")
public class Continent extends CalaisGeoEntity {
    private static final long serialVersionUID = 999179060933099231L;
}
