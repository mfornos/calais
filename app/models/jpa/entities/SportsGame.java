package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SportsGame")
public class SportsGame extends CalaisEntity {

    private static final long serialVersionUID = -1477522411883453129L;

}
