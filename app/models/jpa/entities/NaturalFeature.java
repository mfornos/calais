package models.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NaturalFeature")
public class NaturalFeature extends CalaisEntity {

    private static final long serialVersionUID = 8687494909998189784L;

}
