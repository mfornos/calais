package models.calais.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PublishedMedium")
public class PublishedMedium extends CalaisEntity {

    private static final long serialVersionUID = -766599587646660961L;

}
