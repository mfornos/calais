package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("URL")
public class URL extends CalaisEntity {

    private static final long serialVersionUID = -5399975617479983340L;

}
