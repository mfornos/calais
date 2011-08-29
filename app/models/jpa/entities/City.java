package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("City")
public class City extends CalaisGeoEntity {
    private static final long serialVersionUID = -66230600060472587L;

}
