package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ProvinceOrState")
public class ProvinceOrState extends CalaisGeoEntity {

    private static final long serialVersionUID = 6120472563600056268L;

}
