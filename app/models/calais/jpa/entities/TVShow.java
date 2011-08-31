package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TVShow")
public class TVShow extends CalaisEntity {

    private static final long serialVersionUID = -7391415257743876177L;

}
